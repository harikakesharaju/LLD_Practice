package com.practice.ElevatorSystem;

import java.util.List;
import java.util.Map;

// Dispatches external requests to elevators based on a fixed floor->elevator mapping (if provided)
public class FixedAssignmentDispatcher implements ExternalDispatcher {
    private final List<ElevatorController> controllers;
    private final Map<Integer, Integer> floorToElevator; // exact mapping floor -> elevatorId

    public FixedAssignmentDispatcher(List<ElevatorController> controllers, Map<Integer, Integer> floorToElevator) {
        this.controllers = controllers;
        this.floorToElevator = floorToElevator;
    }

    @Override
    public void dispatch(ExternalRequest request) {
        Integer eid = floorToElevator.get(request.getFloor());
        if (eid != null) {
            ElevatorController chosen = null;
            for (ElevatorController c : controllers) if (c.getId() == eid) chosen = c;
            if (chosen != null) {
                System.out.println("[FixedAssignmentDispatcher] assigning request " + request + " to elevator " + chosen.getId());
                chosen.addExternalRequest(request.getFloor(), request.getDirection());
                return;
            }
        }
        // fallback to least seek time
        System.out.println("[FixedAssignmentDispatcher] fallback to least seek for " + request);
        new LeastSeekTimeDispatcher(controllers).dispatch(request);
    }
}
