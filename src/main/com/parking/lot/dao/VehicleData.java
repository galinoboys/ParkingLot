package com.parking.lot.dao;

import com.parking.lot.dto.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleData {
    private Map<String, Vehicle> vehicleMap;
    private Map<String, List<Vehicle>> colourWiseVehicleMap;

    public VehicleData(){
        vehicleMap = new HashMap<>();
        colourWiseVehicleMap = new HashMap<>();
    }

    public Vehicle getOrAddVehicle(String registrationNo, String colour){
        Vehicle vehicle = null;
        if(vehicleMap.containsKey(registrationNo))
            vehicle = vehicleMap.get(registrationNo);
        else{
            vehicle = new Vehicle(registrationNo, colour);
            synchronized (vehicleMap) {
                if (!vehicleMap.containsKey(registrationNo)) {
                    vehicleMap.put(registrationNo, vehicle);
                    addInColourWiseMap(vehicle);
                }
                else{
                    vehicle = vehicleMap.get(registrationNo);
                }
            }
        }
        return vehicle;
    }

    private void addInColourWiseMap(Vehicle vehicle) {
        new Thread(() -> {
            List<Vehicle> vehicles = null;
            if(colourWiseVehicleMap.containsKey(vehicle.getColour().toUpperCase())){
                vehicles= colourWiseVehicleMap.get(vehicle.getColour().toUpperCase());
            }
            else {
                synchronized (colourWiseVehicleMap){
                    if(!colourWiseVehicleMap.containsKey(vehicle.getColour().toUpperCase())){
                        vehicles = new ArrayList<>();
                        colourWiseVehicleMap.put(vehicle.getColour().toUpperCase(), vehicles);
                    }
                    else {
                        vehicles = colourWiseVehicleMap.get(vehicle.getColour().toUpperCase());
                    }
                }
            }
            synchronized (vehicles){
                vehicles.add(vehicle);
            }
        }).start();
    }

    public Optional<Vehicle> get(String registrationNo) {
        if(vehicleMap.containsKey(registrationNo))
            return Optional.of(vehicleMap.get(registrationNo));
        return Optional.empty();
    }

    public List<Vehicle> getParkedVehiclesWithColour(String colour) {
        if(colourWiseVehicleMap.containsKey(colour.toUpperCase())){
            List<Vehicle> listWithColor = colourWiseVehicleMap.get(colour.toUpperCase());
            return listWithColor.stream().filter(Vehicle::isParked).collect(Collectors.toList());
        }
        else {
            return null;
        }
    }
}
