package com.parking.lot;

import com.parking.lot.service.ParkingService;

import java.util.Random;

public class ParkingLotTester {

    private static ParkingService parkingService;

    public static void main(String[] args) {

        parkingService = ParkingService.getInstance(2, 8, 8, 4);
        Random random = new Random();
        String prefix = "KA-01-HH-";
        String[] colourArray = {"WHITE","Brown","Red","Blue","Grey"};
        for (int i = 0; i < 150; i++) {
            int gateNo = random.nextInt(4)+1;
            int colourId = random.nextInt(colourArray.length);
            System.out.println("Gate no : " +gateNo);
            parkingService.park(prefix+i, colourArray[colourId], gateNo);
            if(i<64)
                parkingService.statusOfParkingFloor(0);
            else
                parkingService.statusOfParkingFloor(1);
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
