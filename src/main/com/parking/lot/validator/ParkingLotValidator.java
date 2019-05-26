package com.parking.lot.validator;

import com.parking.lot.exception.ParkingLotException;
import com.parking.lot.utils.Constants;

public class ParkingLotValidator {

    public static void validateParkingLotInitializationFactor(int noOfFloors, int length, int width, int noOfGates) {
        if(noOfFloors<Constants.MINIMUM_NO_OF_FLOOR_ALLOWED || noOfFloors>Constants.MAXIMUM_NO_OF_FLOOR_ALLOWED)
            throw new ParkingLotException(Constants.MAXIMUM_NO_OF_FLOORS_ALLOWED_IS + Constants.MAXIMUM_NO_OF_FLOOR_ALLOWED);
        if(length<1 || width<1 || length>Constants.MAXIMUM_ALLOWED_CAPACITY_HORI_AND_VERTICAL || width>Constants.MAXIMUM_ALLOWED_CAPACITY_HORI_AND_VERTICAL)
            throw new ParkingLotException(Constants.HORIZONTAL_AND_VERTICAL_SIZE_OF_THE_GRID_PER_FLOOR_IS + Constants.MAXIMUM_ALLOWED_CAPACITY_HORI_AND_VERTICAL);
        if(noOfGates<Constants.MINIMUM_NO_OF_GATES_ALLOWED || noOfGates>Constants.MAXIMUM_NO_OF_GATES_ALLOWED)
            throw new ParkingLotException(Constants.MAXIMUM_NO_OF_GATES_ALLOWED_IS + Constants.MAXIMUM_NO_OF_GATES_ALLOWED);
    }

    public void validateGateNo(int gateNo, int noOfGates) {
        if(gateNo<1 || gateNo>noOfGates)
            throw new ParkingLotException(Constants.INVALID_GATE_NO);
    }

    public void validateFloorNo(int floorNo, int noOfFloors) {
        if(floorNo<0 || floorNo>=noOfFloors)
            throw new ParkingLotException(Constants.INVALID_FLOOR_NO);
    }

}
