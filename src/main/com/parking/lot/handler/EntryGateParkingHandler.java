package com.parking.lot.handler;

import com.parking.lot.dao.ParkingTicketData;
import com.parking.lot.dto.ParkingSlot;
import com.parking.lot.dto.ParkingTicket;
import com.parking.lot.dto.Vehicle;

import java.util.TreeSet;

public abstract class EntryGateParkingHandler {

    ParkingTicketData parkingTicketData;

    public EntryGateParkingHandler() {
        this.parkingTicketData = ParkingTicketData.getInstance();
    }

    public abstract ParkingSlot park(ParkingSlot[][] parkingSlots, Vehicle vehicle, TreeSet<Integer> availableDistance, int length, int width);

    protected ParkingSlot getParkingSlot(ParkingSlot[][] parkingSlots, Vehicle vehicle, int length, int width, int x, int y) {
        ParkingSlot parkingSlot = null;
        if(x>=0 && x<length && y>=0 && y<width){
            if(!parkingSlots[x][y].isOccupied()){
                synchronized (vehicle){
                    ParkingTicket parkingTicket = null;
                    if(!vehicle.isParked()){
                        synchronized (parkingSlots[x][y]){
                            if(!parkingSlots[x][y].isOccupied()){
                                parkingSlot = parkingSlots[x][y];
                                parkingSlot.setOccupied(true);
                                parkingTicket = parkingTicketData.createNewTicket(parkingSlot, vehicle);
                                parkingSlot.setParkingTicket(parkingTicket);
                            }
                        }
                    }
                    if(parkingTicket!=null){
                        vehicle.setParked(true);
                        vehicle.setParkingTicket(parkingTicket);
                    }
                }
            }
        }
        return parkingSlot;
    }

    protected void removeDistance(TreeSet<Integer> availableDistance, int distance) {
        availableDistance.remove(distance);
    }

    protected boolean isDoubleInitialPoint(int length){
        if(length%2==0)
            return true;
        else
            return false;
    }
}
