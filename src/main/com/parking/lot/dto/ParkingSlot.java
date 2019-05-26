package com.parking.lot.dto;

public class ParkingSlot {

    private int id;
    private ParkingTicket parkingTicket;
    private boolean occupied;

    public ParkingSlot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    @Override
    public String toString() {
        if(occupied)
            return String.format("%1$20s", id+"/" +parkingTicket.getVehicle().toString());
        else
            return String.format("%1$20s", id +"/FREE");
    }

    public void markUnOccupied() {
        this.occupied = false;
        this.parkingTicket = null;
    }
}
