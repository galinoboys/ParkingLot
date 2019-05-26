package com.parking.lot.dto;

public class Vehicle {

    private final String registrationNo;
    private final String colour;
    private ParkingTicket parkingTicket;
    private boolean parked;

    public Vehicle(String registrationNo, String colour) {
        this.registrationNo = registrationNo;
        this.colour = colour;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    @Override
    public String toString() {
        return registrationNo;
    }

    public String getColour() {
        return colour;
    }

    public String toString2() {
        return registrationNo +"/"+ colour;
    }

    public void leave() {
        this.parked=false;
        this.parkingTicket=null;
    }
}
