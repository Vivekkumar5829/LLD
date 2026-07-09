package com.system.elevator;

import com.system.elevator.controller.ElevatorController;
import com.system.elevator.enums.Direction;
import com.system.elevator.model.Elevator;
import com.system.elevator.observer.ElevatorDisplay;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        ElevatorController controller =
                ElevatorController.getInstance();

        ElevatorDisplay display =
                new ElevatorDisplay();

        // Add 3 elevators
        Elevator e1 = new Elevator(1, 8);
        Elevator e2 = new Elevator(2, 8);
        Elevator e3 = new Elevator(3, 8);

        e1.addObserver(display);
        e2.addObserver(display);
        e3.addObserver(display);

        controller.addElevator(e1);
        controller.addElevator(e2);
        controller.addElevator(e3);

        // External — floor 5 wants to go UP
        controller.requestElevator(
                5, Direction.UP);

        // Simulate movement
        for (int i = 0; i < 6; i++) {
            controller.stepAll();
        }

        // Internal — inside elevator 1,
        // select floor 9
        controller.requestFloor(1, 9);

        for (int i = 0; i < 5; i++) {
            controller.stepAll();
        }
    }
}