package com.parking.lot.handler.impl;

import com.parking.lot.dto.ParkingSlot;
import com.parking.lot.dto.Vehicle;
import com.parking.lot.handler.EntryGateParkingHandler;

import java.util.TreeSet;

public class WestGateParkingHandler extends EntryGateParkingHandler {

    @Override
    public ParkingSlot park(ParkingSlot[][] parkingSlots, Vehicle vehicle, int length, int width) {
        int maxDistance = (length-1)/2 +width-1;
        if(isDoubleInitialPoint(width)){
            return handleWithDoubleInitialPoint(parkingSlots, vehicle, length, width, maxDistance);
        }
        else {
            return handleWithSingleInitialPoint(parkingSlots, vehicle, length, width, maxDistance);
        }
    }

    private ParkingSlot handleWithSingleInitialPoint(ParkingSlot[][] parkingSlots, Vehicle vehicle, int length, int width, int maxDistance) {
        int entryPointX1=length/2;
        int entryPointY1=0;

        ParkingSlot parkingSlot = null;
        int distance = 0;

        outer:
        while (parkingSlot==null && distance<=maxDistance){

            int x = entryPointX1-distance;
            int y = entryPointY1;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x++;
                y++;
            }while (y<=entryPointY1+distance);

            x = entryPointX1+distance;
            y = entryPointY1;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x--;
                y++;
            }while (y<entryPointY1+distance);
            distance++;
        }

        return parkingSlot;
    }

    private ParkingSlot handleWithDoubleInitialPoint(ParkingSlot[][] parkingSlots, Vehicle vehicle, int length, int width, int maxDistance) {
        int entryPointX1=length/2-1;
        int entryPointY1=0;
        int entryPointX2=length/2;
        int entryPointY2=0;

        ParkingSlot parkingSlot = null;
        int distance = 0;

        outer:
        while (parkingSlot==null && distance<=maxDistance){

            int x = entryPointX1-distance;
            int y = entryPointY1;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x++;
                y++;
            }while (y<=entryPointY1+distance);

            x = entryPointX2+distance;
            y = entryPointY2;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x--;
                y++;
            }while (y<=entryPointY2+distance);
            distance++;
        }

        return parkingSlot;
    }
}
