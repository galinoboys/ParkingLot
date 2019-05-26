package com.parking.lot;

import com.parking.lot.service.ParkingService;

import java.util.Random;

public class ParkingLotTester {

    private static ParkingService parkingService;

    public static void main(String[] args) {

        parkingService = ParkingService.getInstance(3, 8, 7, 3);
        parkingService.park("KA-01-HH-1234", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1235", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1236", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1237", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1238", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1239", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1230", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1231", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1232", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1233", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1241", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1240", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1242", "WHITE", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1243", "brown", 4);
        parkingService.statusOfParkingFloor(0);
        parkingService.park("KA-01-HH-1244", "brown", 4);

        parkingService.printVehicleAt(3000);
        parkingService.printVehicleAt(6002);

        parkingService.printSlotForVehicleNo("KA-01-HH-1230");
        parkingService.leave(6000);
        parkingService.leave(6000);
        parkingService.printSlotForVehicleNo("KA-01-HH-1230");
        parkingService.printSlotForVehicleNo("KA-01-HH-1231");
        parkingService.printAllSlotsWithColour("WHITE");
        parkingService.printAllVehiclesWithColour("WHite");
        parkingService.printAllVehiclesWithColour("Brown");
        parkingService.printAllTickets();

        Random random = new Random();
        String prefix = "KA-01-HH-";
        String colour = "WHITE";
        for (int i = 0; i < 150; i++) {
            int gateNo = random.nextInt(3)+1;
            System.out.println("Gate no : " +gateNo);
            parkingService.park(prefix+i, colour, gateNo);
            if(i<64)
                parkingService.statusOfParkingFloor(0);
            else
                parkingService.statusOfParkingFloor(1);
        }
        /*for (int i = 0; i < 150; i++) {
            int gateNo = random.nextInt(4)+1;
            System.out.println("Gate no : " +gateNo);
            parkingService.leave(prefix+i, colour, gateNo);
            if(i<64)
                parkingService.statusOfParkingFloor(0);
            else
                parkingService.statusOfParkingFloor(1);
        }*/
    }

}
