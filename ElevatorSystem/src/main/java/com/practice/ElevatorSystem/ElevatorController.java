package com.practice.ElevatorSystem;

import java.util.*;

public class ElevatorController {
    private final int id;
    private final ElevatorCar car;

    // Up requests: min-heap (serve nearest higher floor first)
    private final PriorityQueue<Integer> upQueue = new PriorityQueue<>();
    // Down requests: max-heap (serve nearest lower floor first)
    private final PriorityQueue<Integer> downQueue = new PriorityQueue<>(Comparator.reverseOrder());
    // Waiting list to hold requests that will be served when direction changes
    private final List<Integer> waitingList = new ArrayList<>();

    private Direction direction = Direction.IDLE;

    public ElevatorController(int id, ElevatorCar car) {
        this.id = id;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public ElevatorCar getCar() {
        return car;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentFloor() {
        return car.getCurrentFloor();
    }

    public void addExternalRequest(int floor, Direction reqDir) {
        System.out.println("[Controller " + id + "] addExternalRequest floor=" + floor + " dir=" + reqDir);
        addRequestInternal(floor, reqDir);
    }

    public void addInternalRequest(int floor) {
        System.out.println("[Controller " + id + "] addInternalRequest floor=" + floor);
        if (floor == getCurrentFloor()) {
            System.out.println("[Controller " + id + "] already at floor " + floor);
            return;
        }
        Direction reqDir = floor > getCurrentFloor() ? Direction.UP : Direction.DOWN;
        addRequestInternal(floor, reqDir);
    }

    private void addRequestInternal(int floor, Direction reqDir) {
        if (direction == Direction.IDLE) {
            // set direction and add to corresponding queue
            direction = reqDir;
            if (reqDir == Direction.UP) upQueue.offer(floor);
            else downQueue.offer(floor);
            return;
        }

        if (direction == Direction.UP) {
            if (reqDir == Direction.UP) {
                upQueue.offer(floor);
            } else {
                // going up currently, so put down request to waiting
                waitingList.add(floor);
            }
        } else if (direction == Direction.DOWN) {
            if (reqDir == Direction.DOWN) {
                downQueue.offer(floor);
            } else {
                waitingList.add(floor);
            }
        }
    }

    private void refillQueuesFromWaitingIfNeeded() {
        if ((direction == Direction.UP && upQueue.isEmpty() && !waitingList.isEmpty()) ||
            (direction == Direction.DOWN && downQueue.isEmpty() && !waitingList.isEmpty())) {
            // move waiting to the current direction queues according to their relation to current floor
            int current = getCurrentFloor();
            for (Integer f : waitingList) {
                if (f > current) upQueue.offer(f);
                else if (f < current) downQueue.offer(f);
                else { /* same floor, can be ignored */ }
            }
            waitingList.clear();
        }
    }

    // Simulate one time-step: move elevator one step in its direction and service stops
    public void step() {
        // If idle but have pending requests in waiting list, determine direction
        if (direction == Direction.IDLE) {
            if (!upQueue.isEmpty() || !downQueue.isEmpty()) {
                if (!upQueue.isEmpty()) direction = Direction.UP;
                else direction = Direction.DOWN;
            } else if (!waitingList.isEmpty()) {
                // determine direction based on closest waiting request
                int current = getCurrentFloor();
                int target = waitingList.get(0);
                for (int f : waitingList) {
                    if (Math.abs(f - current) < Math.abs(target - current)) target = f;
                }
                waitingList.remove((Integer) target);
                direction = target > current ? Direction.UP : Direction.DOWN;
                if (direction == Direction.UP) upQueue.offer(target); else downQueue.offer(target);
            } else {
                // nothing to do
                return;
            }
        }

        // ensure if current direction queue empty, try refill from waiting
        refillQueuesFromWaitingIfNeeded();

        if (direction == Direction.UP) {
            // if no up targets, but there are down targets, change direction
            if (upQueue.isEmpty()) {
                if (!downQueue.isEmpty()) {
                    direction = Direction.DOWN;
                    step();
                    return;
                } else {
                    direction = Direction.IDLE;
                    return;
                }
            }
            int next = upQueue.peek();
            // move one step towards next
            if (car.getCurrentFloor() < next) {
                car.setCurrentFloor(car.getCurrentFloor() + 1);
                System.out.println("[Controller " + id + "] moving UP to " + car.getCurrentFloor());
            }
            // check if arrived
            if (car.getCurrentFloor() == next) {
                upQueue.poll();
                System.out.println("[Controller " + id + "] stopped at floor " + car.getCurrentFloor());
                // If no more up requests, maybe change direction next time
                if (upQueue.isEmpty() && !downQueue.isEmpty()) direction = Direction.DOWN;
                else if (upQueue.isEmpty() && downQueue.isEmpty() && waitingList.isEmpty()) direction = Direction.IDLE;
            }
        } else if (direction == Direction.DOWN) {
            if (downQueue.isEmpty()) {
                if (!upQueue.isEmpty()) {
                    direction = Direction.UP;
                    step();
                    return;
                } else {
                    direction = Direction.IDLE;
                    return;
                }
            }
            int next = downQueue.peek();
            if (car.getCurrentFloor() > next) {
                car.setCurrentFloor(car.getCurrentFloor() - 1);
                System.out.println("[Controller " + id + "] moving DOWN to " + car.getCurrentFloor());
            }
            if (car.getCurrentFloor() == next) {
                downQueue.poll();
                System.out.println("[Controller " + id + "] stopped at floor " + car.getCurrentFloor());
                if (downQueue.isEmpty() && !upQueue.isEmpty()) direction = Direction.UP;
                else if (upQueue.isEmpty() && downQueue.isEmpty() && waitingList.isEmpty()) direction = Direction.IDLE;
            }
        }
    }

    public boolean isIdle() {
        return direction == Direction.IDLE && upQueue.isEmpty() && downQueue.isEmpty() && waitingList.isEmpty();
    }

    public int estimatedDistanceToFloor(int floor, Direction reqDir) {
        // A simple heuristic: absolute distance plus penalty if moving opposite
        int dist = Math.abs(getCurrentFloor() - floor);
        if (direction == Direction.IDLE) return dist;
        if (direction != reqDir) return dist + 10; // penalize opposite direction
        // if moving towards and will pass floor, smaller penalty
        return dist;
    }

    @Override
    public String toString() {
        return "ElevatorController{" + "id=" + id + 
                ", car=" + car + 
                ", direction=" + direction + 
                ", upQueue=" + upQueue + 
                ", downQueue=" + downQueue +
                ", waiting=" + waitingList + '}';
    }
}
