package com.practice.ElevatorSystem;

import java.util.List;

public class LeastSeekTimeDispatcher implements ExternalDispatcher {
    private final List<ElevatorController> controllers;

    public LeastSeekTimeDispatcher(List<ElevatorController> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void dispatch(ExternalRequest request) {
        ElevatorController best = null;
        int bestScore = Integer.MAX_VALUE;
        for (ElevatorController c : controllers) {
            int score = c.estimatedDistanceToFloor(request.getFloor(), request.getDirection());
            if (score < bestScore) {
                bestScore = score;
                best = c;
            }
        }
        if (best != null) {
            System.out.println("[LeastSeekTimeDispatcher] assigning request " + request + " to elevator " + best.getId());
            best.addExternalRequest(request.getFloor(), request.getDirection());
        } else {
            System.out.println("[LeastSeekTimeDispatcher] no elevator available for " + request);
        }
    }
}
