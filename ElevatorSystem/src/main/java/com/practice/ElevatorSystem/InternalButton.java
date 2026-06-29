package com.practice.ElevatorSystem;

public class InternalButton {
    private final int elevatorId;
    private final int targetFloor;
    private final InternalButtonDispatcher dispatcher;

    public InternalButton(int elevatorId, int targetFloor, InternalButtonDispatcher dispatcher) {
        this.elevatorId = elevatorId;
        this.targetFloor = targetFloor;
        this.dispatcher = dispatcher;
    }

    public void press() {
        System.out.println("[InternalButton] pressed inside elevator " + elevatorId + " to floor " + targetFloor);
        dispatcher.dispatch(elevatorId, new InternalRequest(targetFloor));
    }
}
