package com.parking.lot.validator;

import com.parking.lot.exception.ParkingLotException;
import com.parking.lot.utils.Constants;

public class ParkingLotValidator {

    public void validateGateNo(int gateNo, int noOfGates) {
        if(gateNo<1 || gateNo>noOfGates)
            throw new ParkingLotException(Constants.INVALID_GATE_NO);
    }

    public void validateFloorNo(int floorNo, int noOfFloors) {
        if(floorNo<0 || floorNo>=noOfFloors)
            throw new ParkingLotException(Constants.INVALID_FLOOR_NO);
    }

}
