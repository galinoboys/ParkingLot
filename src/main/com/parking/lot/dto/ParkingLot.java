package com.parking.lot.dto;

import com.parking.lot.dao.ParkingTicketData;
import com.parking.lot.dao.VehicleData;
import com.parking.lot.exception.ParkingLotException;
import com.parking.lot.utils.Constants;
import com.parking.lot.utils.Util;
import com.parking.lot.validator.ParkingLotValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ParkingLot {

    private static ParkingLot parkingLot;

    private final int noOfFloors;
    private final int noOfGates;
    private List<ParkingFloor> parkingFloors;
    private VehicleData vehicleData;
    private ParkingLotValidator parkingLotValidator;
    private ParkingTicketData parkingTicketData;

    public static ParkingLot getInstance(int noOfFloors, int length, int width, int noOfGates){
        ParkingLotValidator.validateParkingLotInitializationFactor(noOfFloors, length, width, noOfGates);
        if(parkingLot==null){
            synchronized (ParkingLot.class){
                if(parkingLot==null)
                    parkingLot = new ParkingLot(noOfFloors, length, width, noOfGates);
            }
        }
        return parkingLot;
    }

    private ParkingLot(int noOfFloors, int length, int width, int noOfGates){
        this.noOfFloors = noOfFloors;
        this.noOfGates = noOfGates;
        parkingFloors = new ArrayList<>(noOfFloors);
        vehicleData = VehicleData.getInstance();
        parkingLotValidator = ParkingLotValidator.getInstance();
        parkingTicketData = ParkingTicketData.getInstance();
        for (int i = 0; i < noOfFloors; i++) {
            parkingFloors.add(new ParkingFloor(i, length, width, noOfGates));
        }
    }

    public ParkingSlot park(String registrationNo, String colour, int gateNo){
        parkingLotValidator.validateGateNo(gateNo, noOfGates);
        Vehicle vehicle = vehicleData.getOrAddVehicle(registrationNo, colour);
        if(vehicle.isParked())
            throw new ParkingLotException(Constants.VEHICLE_IS_ALREADY_PARKED);
        ParkingSlot parkingSlot = null;
        while (parkingSlot==null){
            ParkingFloor parkingFloor = getFirstFreeParkingFloor();
            if(parkingFloor==null)
                throw new ParkingLotException(Constants.SORRY_PARKING_LOT_IS_FULL);
            parkingSlot = parkingFloor.park(vehicle, gateNo);
            if(parkingSlot!=null)
                break;
        }
        return parkingSlot;

    }

    public boolean leave(int parkingSlotId){
        int floorNo = Util.getFloorFromParkingSlotId(parkingSlotId);
        parkingLotValidator.validateFloorNo(floorNo, noOfFloors);
        return parkingFloors.get(floorNo).leave(parkingSlotId);
    }

    private ParkingFloor getFirstFreeParkingFloor() {
        for (ParkingFloor parkingFloor : parkingFloors){
            if(!parkingFloor.isFull())
                return parkingFloor;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ParkingFloor parkingFloor:
             parkingFloors) {
            sb.append(parkingFloor+"\n\n\n");
        }
        return sb.toString();
    }

    public ParkingFloor statusOfParkingFloor(int floorNo) {
        parkingLotValidator.validateFloorNo(floorNo, noOfFloors);
        return parkingFloors.get(floorNo);
    }

    public Optional<Vehicle> getVehicleAt(int parkingSlotId) {
        int floorNo = Util.getFloorFromParkingSlotId(parkingSlotId);
        parkingLotValidator.validateFloorNo(floorNo, noOfFloors);
        Optional<Vehicle> vehicleOp = parkingFloors.get(floorNo).getVehicleAt(parkingSlotId);
        return vehicleOp;
    }

    public ParkingSlot getParkingSlotForVehicle(String registrationNo) {
        Optional<Vehicle> vehicleOp = vehicleData.get(registrationNo);
        if(vehicleOp.isPresent()){
            Vehicle vehicle = vehicleOp.get();
            if(vehicle.isParked())
                return Util.getParkingSlotFromVehicle(vehicle);
            else
                throw new ParkingLotException(Constants.VEHICLE_IS_NOT_PARKED);
        }
        else
            throw new ParkingLotException(Constants.NO_VEHICLE_FOUND_FOR_THE_REGISTRATION_NO +registrationNo);
    }

    public List<Vehicle> getParkedVehiclesWithColour(String colour){
        return vehicleData.getParkedVehiclesWithColour(colour);
    }

    public Collection<ParkingTicket> getAllParkingTickets() {
        return parkingTicketData.getAllTickets();
    }
}
