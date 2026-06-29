package com.practice.ElevatorSystem;

public class InternalRequest {
    private final int targetFloor;

    public InternalRequest(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    @Override
    public String toString() {
        return "InternalRequest{targetFloor=" + targetFloor + '}';
    }
}
