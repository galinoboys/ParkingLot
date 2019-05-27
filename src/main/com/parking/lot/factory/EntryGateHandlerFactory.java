package com.parking.lot.factory;

import com.parking.lot.handler.EntryGateParkingHandler;
import com.parking.lot.handler.impl.EastGateParkingHandler;
import com.parking.lot.handler.impl.NorthGateParkingHandler;
import com.parking.lot.handler.impl.SouthGateParkingHandler;
import com.parking.lot.handler.impl.WestGateParkingHandler;
import com.parking.lot.validator.ParkingLotValidator;

public class EntryGateHandlerFactory {

    private static EntryGateHandlerFactory singeltonObject;

    public static EntryGateHandlerFactory getInstance(){
        if(singeltonObject ==null){
            synchronized (EntryGateHandlerFactory.class){
                if(singeltonObject ==null)
                    singeltonObject = new EntryGateHandlerFactory();
            }
        }
        return singeltonObject;
    }

    private EntryGateHandlerFactory() {
    }

    public EntryGateParkingHandler getHandler(int gateNo){
        switch (gateNo){
            case 1:
                return NorthGateParkingHandler.getInstance();
            case 2:
                return EastGateParkingHandler.getInstance();
            case 3 :
                return SouthGateParkingHandler.getInstance();
            case 4 :
                return WestGateParkingHandler.getInstance();
            default :
                return null;
        }

    }
}
