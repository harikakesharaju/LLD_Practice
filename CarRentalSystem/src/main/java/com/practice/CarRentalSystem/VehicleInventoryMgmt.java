package com.practice.CarRentalSystem;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class VehicleInventoryMgmt {
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void allotVehicle(Request req) throws Exception {
        Vehicle veh = vehicles.stream()
                .filter(v -> v.getNoseats() == req.getSeats()
                        && v.getType() == req.getType()
                        && v.getStatus() == VehicleStatus.AVAILABLE)
                .findFirst()
                .orElseThrow(() -> new Exception("No vehicle available"));

        veh.setStatus(VehicleStatus.UNAVALABLE);
        req.setAllottedV(veh);
        req.setReqstatus(ReqStatus.RESERVED);
    }

    public int getFare(Request req) {
        int hours = (int) ChronoUnit.HOURS.between(req.getFrom(), req.getTo());
        return req.getAllottedV().getHourlyCost() * hours;
    }
}
