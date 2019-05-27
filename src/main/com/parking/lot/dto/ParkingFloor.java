package com.parking.lot.dto;

import com.parking.lot.factory.EntryGateHandlerFactory;
import com.parking.lot.utils.Constants;
import com.parking.lot.utils.Util;

import java.util.*;

public class ParkingFloor {

    private final Integer maximumCapacity;
    private Integer usedCapacity;
    private final int floorNo;

    private final int length;
    private final int width;

    private ParkingSlot[][] parkingSlots;
    private EntryGate[] entryGates;
    private EntryGateHandlerFactory factory;

    public ParkingFloor(int floorNo, int length, int width, int noOfGates) {
        this.floorNo = floorNo;
        this.length = length;
        this.width = width;
        this.maximumCapacity = length*width;
        this.usedCapacity = 0;

        this.parkingSlots = new ParkingSlot[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                parkingSlots[i][j] = new ParkingSlot(floorNo* Constants.MAXIMUM_ALLOWED_CAPACITY + (i*Constants.MAXIMUM_ALLOWED_CAPACITY_HORI_AND_VERTICAL +j));
            }
        }

        entryGates = new EntryGate[noOfGates];
        for (int i = 0; i < noOfGates; i++) {
            entryGates[i] = new EntryGate(i+1, this.length, this.width);
        }
        factory = EntryGateHandlerFactory.getInstance();
    }

    public boolean isFull() {
        if(maximumCapacity==usedCapacity)
            return true;
        return false;
    }

    public ParkingSlot park(Vehicle vehicle, int gateNo) {
        ParkingSlot parkingSlot = factory.getHandler(gateNo).park(parkingSlots, vehicle, length, width);
        if(parkingSlot!=null)
            incrementUsedCapacity();
        return parkingSlot;
    }

    private void incrementUsedCapacity() {
        synchronized (this.usedCapacity){
            this.usedCapacity++;
        }
    }

    private void decrementUsedCapacity() {
        synchronized (this.usedCapacity){
            this.usedCapacity--;
        }
    }

    public Optional<Vehicle> getVehicleAt(int slotFromParkingSlotId) {
        int i = Util.getXCoOrdinateFromSlotNo(slotFromParkingSlotId);
        int j = Util.getYCoOrdinateFromSlotNo(slotFromParkingSlotId);
        if(i<0 || i>=length || j<0 || j>=width)
            throw new RuntimeException(Constants.INVALID_PARKING_SLOT);
        if(parkingSlots[i][j].isOccupied()){
            Optional.of(parkingSlots[i][j].getParkingTicket().getVehicle());
        }
        return Optional.empty();
    }

    public boolean leave(int slotFromParkingSlotId) {
        int x = Util.getXCoOrdinateFromSlotNo(slotFromParkingSlotId);
        int y = Util.getYCoOrdinateFromSlotNo(slotFromParkingSlotId);
        if(x<0 || x>=length || y<0 || y>=width)
            throw new RuntimeException(Constants.INVALID_PARKING_SLOT);
        ParkingSlot parkingSlot = parkingSlots[x][y];
        if(!parkingSlot.isOccupied())
            return false;
        Vehicle vehicle = null;
        ParkingTicket parkingTicket = null;
        synchronized (parkingSlot){
            if(parkingSlot.isOccupied()){
                parkingTicket = parkingSlot.getParkingTicket();
                vehicle = parkingTicket.getVehicle();
                parkingSlot.markUnOccupied();
                parkingTicket.setInactive();
            }
            if(vehicle!=null){
                synchronized (vehicle){
                    vehicle.leave();
                }
                decrementUsedCapacity();
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking floor no : "+ floorNo+ ", ");
        sb.append("Used Capacity : "+ usedCapacity+ "\n");
        for (int i = 0; i < length; i++) {
            sb.append(Arrays.toString(parkingSlots[i])+"\n");
        }
        sb.append("\n\n\n");
        return sb.toString();
    }
}
