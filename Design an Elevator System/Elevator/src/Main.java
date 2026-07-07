import com.elevatorsystem.Elevator;
import com.elevatorsystem.ElevatorController;
import com.elevatorsystem.ElevatorDisplay;
import com.elevatorsystem.enums.Direction;

public class Main {
    public static void main(String[] args) {
        ElevatorController controller = ElevatorController.getInstance();

        controller.addElevator(new Elevator(1, 8));
        controller.addElevator(new Elevator(2, 8));
        controller.addElevator(new Elevator(3, 8));

        ElevatorDisplay display = new ElevatorDisplay();
        for (Elevator e : controller.getElevators()) {
            e.addObserver(display);
        }

        // External: someone on floor 5 wants to go up
        controller.requestElevator(5, Direction.UP);

        // Simulate steps until elevator arrives
        for (int i = 0; i < 6; i++) {
            controller.stepAll();
        }

        // Internal: inside elevator 1, select floor 9
        controller.requestFloor(1, 9);

        for (int i = 0; i < 5; i++) {
            controller.stepAll();
        }
    }
}