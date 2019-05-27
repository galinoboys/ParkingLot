package com.parking.lot.handler.impl;

import com.parking.lot.dto.ParkingSlot;
import com.parking.lot.dto.Vehicle;
import com.parking.lot.handler.EntryGateParkingHandler;

import java.util.TreeSet;

public class SouthGateParkingHandler extends EntryGateParkingHandler {

    private static SouthGateParkingHandler singeltonObject;

    public static SouthGateParkingHandler getInstance(){
        if(singeltonObject ==null){
            synchronized (SouthGateParkingHandler.class){
                if(singeltonObject ==null)
                    singeltonObject = new SouthGateParkingHandler();
            }
        }
        return singeltonObject;
    }

    private SouthGateParkingHandler() {
    }

    @Override
    public ParkingSlot park(ParkingSlot[][] parkingSlots, Vehicle vehicle, int length, int width) {
        int maxDistance = length+(width-1)/2-1;
        if(isDoubleInitialPoint(width)){
            return handleWithDoubleInitialPoint(parkingSlots, vehicle, length, width, maxDistance);
        }
        else {
            return handleWithSingleInitialPoint(parkingSlots, vehicle, length, width, maxDistance);
        }
    }

    private ParkingSlot handleWithSingleInitialPoint(ParkingSlot[][] parkingSlots, Vehicle vehicle, int length, int width, int maxDistance) {
        int entryPointX1=length-1;
        int entryPointY1=width/2;

        ParkingSlot parkingSlot = null;
        int distance = 0;

        outer:
        while (parkingSlot==null && distance<=maxDistance){

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
            distance++;
        }

        return parkingSlot;
    }

    private ParkingSlot handleWithDoubleInitialPoint(ParkingSlot[][] parkingSlots, Vehicle vehicle, int length, int width, int maxDistance) {
        int entryPointX1=length-1;
        int entryPointY1=width/2-1;
        int entryPointX2=length-1;
        int entryPointY2=width/2;

        ParkingSlot parkingSlot = null;
        int distance = 0;

        outer:
        while (parkingSlot==null && distance<=maxDistance){

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
            distance++;
        }

        return parkingSlot;
    }
}
