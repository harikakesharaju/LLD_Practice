package com.practice.ElevatorSystem;

public interface InternalButtonDispatcher {
    void dispatch(int elevatorId, InternalRequest request);
}
