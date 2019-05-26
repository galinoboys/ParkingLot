package com.parking.lot.utils;

import com.parking.lot.dto.ParkingSlot;
import com.parking.lot.dto.ParkingTicket;
import com.parking.lot.dto.Vehicle;

public class Util {


    public static int getFloorFromParkingSlotId(int parkingSlotId) {
        return parkingSlotId/Constants.MAXIMUM_ALLOWED_CAPACITY;
    }

    public static int getSlotFromParkingSlotId(int parkingSlotId) {
        return parkingSlotId%Constants.MAXIMUM_ALLOWED_CAPACITY;
    }

    public static int getXCoOrdinateFromSlotNo(int slotFromParkingSlotId) {
        return (slotFromParkingSlotId%Constants.MAXIMUM_ALLOWED_CAPACITY) / Constants.MAXIMUM_ALLOWED_CAPACITY_HORI_AND_VERTICAL;
    }
    public static int getYCoOrdinateFromSlotNo(int slotFromParkingSlotId) {
        return slotFromParkingSlotId%Constants.MAXIMUM_ALLOWED_CAPACITY_HORI_AND_VERTICAL;
    }

    public static int getParkingSlotIdFromVehicle(Vehicle vehicle) {
        ParkingTicket parkingTicket = vehicle.getParkingTicket();
        if(parkingTicket!=null){
            ParkingSlot parkingSlot = parkingTicket.getParkingSlot();
            if(parkingSlot!=null)
                return parkingSlot.getId();
        }
        return -1;
    }

    public static ParkingSlot getParkingSlotFromVehicle(Vehicle vehicle) {
        ParkingTicket parkingTicket = vehicle.getParkingTicket();
        if(parkingTicket!=null){
            ParkingSlot parkingSlot = parkingTicket.getParkingSlot();
            if(parkingSlot!=null)
                return parkingSlot;
        }
        return new ParkingSlot(-1);
    }
}
