package com.practice.ElevatorSystem;

public class ElevatorCar {
    private final int id;
    private int currentFloor;
    private final int capacity;

    public ElevatorCar(int id, int initialFloor, int capacity) {
        this.id = id;
        this.currentFloor = initialFloor;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "ElevatorCar{id=" + id + ", currentFloor=" + currentFloor + '}';
    }
}
