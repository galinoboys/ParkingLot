package com.parking.lot.handler.impl;

import com.parking.lot.dto.ParkingSlot;
import com.parking.lot.dto.Vehicle;
import com.parking.lot.handler.EntryGateParkingHandler;

public class NorthGateParkingHandler extends EntryGateParkingHandler {

    private static NorthGateParkingHandler singeltonObject;

    public static NorthGateParkingHandler getInstance(){
        if(singeltonObject ==null){
            synchronized (NorthGateParkingHandler.class){
                if(singeltonObject ==null)
                    singeltonObject = new NorthGateParkingHandler();
            }
        }
        return singeltonObject;
    }

    private NorthGateParkingHandler() {
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
        int entryPointX1=0;
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
                x++;
                y++;
            }while (x<=entryPointX1+distance);

            x = entryPointX1;
            y = entryPointY1+distance;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x++;
                y--;
            }while (x<entryPointX1+distance);
            distance++;
        }

        return parkingSlot;
    }

    private ParkingSlot handleWithDoubleInitialPoint(ParkingSlot[][] parkingSlots, Vehicle vehicle, int length, int width, int maxDistance) {
        int entryPointX1=0;
        int entryPointY1=width/2-1;
        int entryPointX2=0;
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
                x++;
                y++;
            }while (x<=entryPointX1+distance);

            x = entryPointX2;
            y = entryPointY2+distance;
            do{
                parkingSlot = getParkingSlot(parkingSlots, vehicle, length, width, x, y);
                if(parkingSlot!=null)
                    break outer;
                x++;
                y--;
            }while (x<=entryPointX1+distance);
            distance++;
        }

        return parkingSlot;
    }
}
