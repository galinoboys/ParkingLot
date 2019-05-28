package com.parking.lot.service.impl;

import com.parking.lot.dto.*;
import com.parking.lot.exception.ParkingLotException;
import com.parking.lot.service.ParkingService;
import com.parking.lot.utils.Constants;
import com.parking.lot.utils.Util;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ParkingServiceImpl implements ParkingService {

    private static ParkingServiceImpl parkingService;
    private ParkingLot parkingLot;

    public static ParkingServiceImpl getInstance(int noOfFloors, int length, int width, int noOfGates){
        if(parkingService==null){
            synchronized (ParkingServiceImpl.class){
                if(parkingService==null)
                    parkingService = new ParkingServiceImpl(noOfFloors, length, width, noOfGates);
            }
        }
        return parkingService;
    }

    private ParkingServiceImpl(int noOfFloors, int length, int width, int noOfGates) {
        parkingLot = ParkingLot.getInstance(noOfFloors, length, width, noOfGates);
    }

    @Override
    public void park(String registrationNo, String white, int gateNo) {
        try {
            ParkingSlot parkingSlot = parkingLot.park(registrationNo, white, gateNo);
            System.out.println(Constants.ALLOCATED_SLOT_NO +parkingSlot.getId());
        }
        catch (ParkingLotException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(Constants.OPPS_SOMETHING_WENT_WRONG);

        }
    }

    @Override
    public void leave(int parkingSlotId) {
        try {
            boolean leave = parkingLot.leave(parkingSlotId);
            if(!leave)
                System.out.println(Constants.NO_VEHICLE_FOUND_AT_PARKING_SLOT +parkingSlotId);
            else
                System.out.printf(Constants.SLOT_NO_S_IS_FREE, parkingSlotId);
        }
        catch (ParkingLotException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(Constants.OPPS_SOMETHING_WENT_WRONG);

        }
    }

    @Override
    public void statusOfParkingFloor(int floorNo) {
        try {
            ParkingFloor parkingFloor = parkingLot.statusOfParkingFloor(floorNo);
            System.out.println(parkingFloor);
        }
        catch (ParkingLotException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(Constants.OPPS_SOMETHING_WENT_WRONG);

        }
    }

    @Override
    public void printVehicleAt(int parkingSlotId) {
        try {
            Optional<Vehicle> vehicleOp = parkingLot.getVehicleAt(parkingSlotId);
            if(!vehicleOp.isPresent())
                System.out.println(Constants.NO_VEHICLE_FOUND_AT_PARKING_SLOT +parkingSlotId);
            else
                System.out.println(vehicleOp.get().toString2());
        }
        catch (ParkingLotException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(Constants.OPPS_SOMETHING_WENT_WRONG);

        }
    }

    @Override
    public void printSlotForVehicleNo(String registrationNo) {
        try {
            ParkingSlot parkingSlot = parkingLot.getParkingSlotForVehicle(registrationNo);
            System.out.println(parkingSlot.toString());
        }
        catch (ParkingLotException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(Constants.OPPS_SOMETHING_WENT_WRONG);

        }
    }

    @Override
    public void printAllSlotsWithColour(String colour) {
        try {
            List<Vehicle> vehicles = parkingLot.getParkedVehiclesWithColour(colour);
            if(vehicles!=null){
                StringBuilder sb = new StringBuilder("\n" +colour + " : ");
                vehicles.stream().forEach(t-> sb.append(Util.getParkingSlotIdFromVehicle(t)+","));
                System.out.println(sb.toString());
            }
            else
                System.out.println(Constants.NO_VEHICLE_FOUND_WITH_THE_GIVEN_COLOUR);
        }
        catch (ParkingLotException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(Constants.OPPS_SOMETHING_WENT_WRONG);

        }
    }

    @Override
    public void printAllVehiclesWithColour(String colour) {
        try {
            List<Vehicle> vehicles = parkingLot.getParkedVehiclesWithColour(colour);
            if(vehicles!=null){
                StringBuilder sb = new StringBuilder("\n" +colour + " : ");
                vehicles.stream().forEach(t-> sb.append(t.getRegistrationNo()+","));
                System.out.println(sb.toString());
            }
            else
                System.out.println(Constants.NO_VEHICLE_FOUND_WITH_THE_GIVEN_COLOUR);
        }
            catch (ParkingLotException e) {
            System.out.println(e.getMessage());
        }
            catch (Exception e){
            System.out.println(Constants.OPPS_SOMETHING_WENT_WRONG);

        }
    }

    @Override
    public void printAllTickets(){
        Collection<ParkingTicket> parkingTickets = parkingLot.getAllParkingTickets();
        StringBuilder sb = new StringBuilder("\n\n\n\nParking tickets : \n");
        parkingTickets.stream().forEach(t-> sb.append(t.toString()+"\n"));
        System.out.println(sb.toString());
    }
}
