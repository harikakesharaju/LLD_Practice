package com.practice.ElevatorSystem;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class InternalButtonDispatcherImpl implements InternalButtonDispatcher {
    private final Map<Integer, ElevatorController> controllers = new HashMap<>();

    public InternalButtonDispatcherImpl(List<ElevatorController> list) {
        for (ElevatorController c : list) controllers.put(c.getId(), c);
    }

    @Override
    public void dispatch(int elevatorId, InternalRequest request) {
        ElevatorController controller = controllers.get(elevatorId);
        if (controller == null) {
            System.out.println("[InternalDispatcher] unknown elevator id " + elevatorId);
            return;
        }
        controller.addInternalRequest(request.getTargetFloor());
    }
}
