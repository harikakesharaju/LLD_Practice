package com.practice.ElevatorSystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo application for the elevator system.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        // Create building with floors 0..10 and 3 elevators
        // Use least seek time dispatcher for external requests
        Building building;
        {
            building = new Building(0, 10, 3, null);
            // set up external dispatcher: demonstrate both strategies
            // we'll use least seek time here by default
            building = new Building(0, 10, 3, new LeastSeekTimeDispatcher(building.getControllers()));
        }

        // Press some external buttons
        ExternalButton eb1 = new ExternalButton(3, Direction.UP, building.getExternalDispatcher());
        ExternalButton eb2 = new ExternalButton(7, Direction.DOWN, building.getExternalDispatcher());
        ExternalButton eb3 = new ExternalButton(1, Direction.UP, building.getExternalDispatcher());

        eb1.press();
        eb2.press();
        eb3.press();

        // Simulate a few steps
        for (int t = 0; t < 8; t++) {
            System.out.println("\n== Tick " + t + " ==");
            // at tick 2, simulate someone inside elevator 0 pressing floor 9
            if (t == 2) {
                InternalButton ib = new InternalButton(0, 9, building.getInternalDispatcher());
                ib.press();
            }
            building.stepAllControllers();
            building.printStatus();
            Thread.sleep(300); // small pause for readability when run interactively
        }

        // Demonstrate fixed assignment dispatcher (e.g., elevator 0 for floors 0-3, 1 for 4-7, 2 for 8-10)
        System.out.println("\nSwitching to FixedAssignmentDispatcher and issuing external request at floor 2");
        Map<Integer, Integer> map = new HashMap<>();
        for (int f = 0; f <= 3; f++) map.put(f, 0);
        for (int f = 4; f <= 7; f++) map.put(f, 1);
        for (int f = 8; f <= 10; f++) map.put(f, 2);
        FixedAssignmentDispatcher fixed = new FixedAssignmentDispatcher(building.getControllers(), map);
        ExternalButton eb4 = new ExternalButton(2, Direction.UP, fixed);
        eb4.press();

        for (int t = 8; t < 14; t++) {
            System.out.println("\n== Tick " + t + " ==");
            building.stepAllControllers();
            building.printStatus();
            Thread.sleep(300);
        }

        System.out.println("Demo finished.");
    }
}
