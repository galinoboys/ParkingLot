package com.parking.lot.service;

public interface ParkingService {

    void park(String registrationNo, String white, int gateNo);

    void leave(int parkingSlotId);

    void statusOfParkingFloor(int floorNo);

    void printVehicleAt(int parkingSlotId);

    void printSlotForVehicleNo(String registrationNo);

    void printAllSlotsWithColour(String colour);

    void printAllVehiclesWithColour(String colour);

    void printAllTickets();
}
