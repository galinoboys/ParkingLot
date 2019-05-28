package com.parking.lot.dao;

import com.parking.lot.dto.ParkingSlot;
import com.parking.lot.dto.ParkingTicket;
import com.parking.lot.dto.Vehicle;

import java.util.*;

public class ParkingTicketData {

    private Integer identifier = 1;
    private Map<Integer, ParkingTicket> parkingTickets;

    private static ParkingTicketData parkingTicketData;

    public static ParkingTicketData getInstance(){
        if(parkingTicketData==null){
            synchronized (ParkingTicketData.class){
                if(parkingTicketData==null)
                    parkingTicketData = new ParkingTicketData();
            }
        }
        return parkingTicketData;
    }

    private ParkingTicketData(){
        parkingTickets = new HashMap<>();
    }

    private int getIdentifier(){
        int id = -1;
        synchronized (identifier){
            id = identifier++;
        }
        return id;
    }

    public ParkingTicket createNewTicket(ParkingSlot parkingSlot, Vehicle vehicle) {
        int identifier = getIdentifier();
        ParkingTicket parkingTicket = new ParkingTicket(identifier, parkingSlot, vehicle, new Date());
        parkingTickets.put(identifier, parkingTicket);
        return parkingTicket;
    }


    public Collection<ParkingTicket> getAllTickets() {
        return parkingTickets.values();
    }
}
