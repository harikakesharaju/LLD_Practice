package com.practice.ElevatorSystem;

public class ExternalButton {
    private final int floor;
    private final Direction direction;
    private final ExternalDispatcher dispatcher;

    public ExternalButton(int floor, Direction direction, ExternalDispatcher dispatcher) {
        this.floor = floor;
        this.direction = direction;
        this.dispatcher = dispatcher;
    }

    public void press() {
        System.out.println("[ExternalButton] pressed at floor " + floor + " " + direction);
        dispatcher.dispatch(new ExternalRequest(floor, direction));
    }
}
