package com.parking.lot.factory;

import com.parking.lot.handler.EntryGateParkingHandler;
import com.parking.lot.handler.impl.EastGateParkingHandler;
import com.parking.lot.handler.impl.NorthGateParkingHandler;
import com.parking.lot.handler.impl.SouthGateParkingHandler;
import com.parking.lot.handler.impl.WestGateParkingHandler;

public class EntryGateHandlerFactory {

    public EntryGateParkingHandler getHandler(int gateNo){
        switch (gateNo){
            case 1:
                return new NorthGateParkingHandler();
            case 2:
                return new EastGateParkingHandler();
            case 3 :
                return new SouthGateParkingHandler();
            case 4 :
                return new WestGateParkingHandler();
            default :
                return null;
        }

    }
}
