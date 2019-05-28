package com.parking.lot;

import com.parking.lot.service.impl.ParkingServiceImpl;

import java.util.Random;

public class ParkingLotTester {

    private static ParkingServiceImpl parkingService;

    public static void main(String[] args) {

        int noOfFloors = 2;
        int length = 8;
        int width = 5;
        int noOfGates = 4;


        parkingService = ParkingServiceImpl.getInstance(noOfFloors, length, width, noOfGates);
        Random random = new Random();
        String prefix = "KA-01-HH-";
        String[] colourArray = {"WHITE","Brown","Red","Blue","Grey"};
        for (int i = 0; i < 150; i++) {
            int gateNo = random.nextInt(4)+1;
            int colourId = random.nextInt(colourArray.length);
            System.out.println("Gate no : " +gateNo);
            parkingService.park(prefix+i, colourArray[colourId], gateNo);

            parkingService.statusOfParkingFloor(Integer.min(i/(length*width), noOfFloors-1));
        }
        parkingService.statusOfParkingFloor(9);

        parkingService.printVehicleAt(3000);
        parkingService.printVehicleAt(6002);

        parkingService.printSlotForVehicleNo("KA-01-HH-150");
        parkingService.leave(6000);
        parkingService.leave(6000);
        parkingService.printSlotForVehicleNo("KA-01-HH-20");
        parkingService.printSlotForVehicleNo("KA-01-HH-155");
        for (int i = 0; i < colourArray.length; i++) {
            parkingService.printAllVehiclesWithColour(colourArray[i]);
            parkingService.printAllSlotsWithColour(colourArray[i]);
        }
        parkingService.printAllTickets();
    }

}
