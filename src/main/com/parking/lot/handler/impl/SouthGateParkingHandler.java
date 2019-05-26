package com.parking.lot.handler.impl;

import com.parking.lot.dto.ParkingSlot;
import com.parking.lot.dto.Vehicle;
import com.parking.lot.handler.EntryGateParkingHandler;

import java.util.TreeSet;

public class SouthGateParkingHandler extends EntryGateParkingHandler {

    @Override
    public ParkingSlot park(ParkingSlot[][] parkingSlots, Vehicle vehicle, TreeSet<Integer> availableDistance, int length, int width) {
        if(isDoubleInitialPoint(width)){
            return handleWithDoubleInitialPoint(parkingSlots, vehicle, availableDistance, length, width);
        }
        else {
            return handleWithSingleInitialPoint(parkingSlots, vehicle, availableDistance, length, width);
        }
    }

    private ParkingSlot handleWithSingleInitialPoint(ParkingSlot[][] parkingSlots, Vehicle vehicle, TreeSet<Integer> availableDistance, int length, int width) {
        int entryPointX1=length-1;
        int entryPointY1=width/2;

        ParkingSlot parkingSlot = null;
        int distance = -1;

        outer:
        while (parkingSlot==null && !availableDistance.isEmpty()){
            distance = availableDistance.first();
            int x = entryPointX1;
            int y = entryPointY1-distance;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x--;
                y++;
            }while (x>=entryPointX1-distance);

            x = entryPointX1;
            y = entryPointY1+distance;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x--;
                y--;
            }while (x>entryPointX1-distance);
            removeDistance(availableDistance, distance);
        }

        return parkingSlot;
    }

    private ParkingSlot handleWithDoubleInitialPoint(ParkingSlot[][] parkingSlots, Vehicle vehicle, TreeSet<Integer> availableDistance, int length, int width) {
        int entryPointX1=length-1;
        int entryPointY1=width/2-1;
        int entryPointX2=length-1;
        int entryPointY2=width/2;

        ParkingSlot parkingSlot = null;
        int distance = -1;

        outer:
        while (parkingSlot==null && !availableDistance.isEmpty()){
            distance = availableDistance.first();
            int x = entryPointX1;
            int y = entryPointY1-distance;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x--;
                y++;
            }while (x>=entryPointX1-distance);

            x = entryPointX2;
            y = entryPointY2+distance;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x--;
                y--;
            }while (x>=entryPointX1-distance);
            removeDistance(availableDistance, distance);
        }

        return parkingSlot;
    }
}
