package com.parking.lot.dto;

import java.util.Date;

public class ParkingTicket {

    private int id;
    private ParkingSlot parkingSlot;
    private Vehicle vehicle;
    private Date inTime;
    private Date outTime;
    private boolean active;

    public ParkingTicket(int id, ParkingSlot parkingSlot, Vehicle vehicle, Date inTime) {
        this.id = id;
        this.parkingSlot = parkingSlot;
        this.vehicle = vehicle;
        this.inTime = inTime;
        this.active=true;
    }

    public int getId() {
        return id;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Date getInTime() {
        return inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setInactive() {
        this.active = false;
        this.outTime = new Date();
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "id=" + id +
                ", parkingSlot=" + parkingSlot +
                ", vehicle=" + vehicle +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", active=" + active +
                '}';
    }
}
