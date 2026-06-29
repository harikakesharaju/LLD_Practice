package com.practice.ElevatorSystem;

import java.util.*;

public class Building {
    private final int minFloor;
    private final int maxFloor;
    private final List<Floor> floors = new ArrayList<>();
    private final List<ElevatorController> controllers = new ArrayList<>();
    private final InternalButtonDispatcher internalDispatcher;
    private final ExternalDispatcher externalDispatcher;

    public Building(int minFloor, int maxFloor, int numElevators,
                    ExternalDispatcher externalDispatcherStrategy) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        for (int f = minFloor; f <= maxFloor; f++) floors.add(new Floor(f));

        // create elevators at ground (minFloor)
        for (int i = 0; i < numElevators; i++) {
            ElevatorCar car = new ElevatorCar(i, minFloor, 10);
            controllers.add(new ElevatorController(i, car));
        }

        this.internalDispatcher = new InternalButtonDispatcherImpl(controllers);
        this.externalDispatcher = externalDispatcherStrategy;
    }

    public List<ElevatorController> getControllers() {
        return controllers;
    }

    public InternalButtonDispatcher getInternalDispatcher() {
        return internalDispatcher;
    }

    public ExternalDispatcher getExternalDispatcher() {
        return externalDispatcher;
    }

    public void stepAllControllers() {
        for (ElevatorController c : controllers) c.step();
    }

    public void printStatus() {
        System.out.println("--- Building Status ---");
        for (ElevatorController c : controllers) {
            System.out.println(c);
        }
    }
}
