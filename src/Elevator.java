import java.util.ArrayList;
import java.util.Collections;

public class Elevator {
    /** The floor the elevator is currently on. */
    private int currentFloor = 0;

    /** The ID number of the elevator. */
    private final int id;

    /** The direction the elevator is going, which begins at STOP. */
    private Direction currentDirection = Direction.STOP;

    /** The ArrayList containing the list of calls the elevator has received. */
    private final ArrayList<Integer> destinations = new ArrayList<>();

    /**
    Constructs an elevator given a building and an ID number and sets it on floor 0.
    An array of destinations with an initial length of 50 is created.
    @param id The ID number of the elevator.
    */
    public Elevator(final int id) {
        this.id = id;
    }
	
    @Override
    public String toString() {
        return "\t\tElevator #" + id + ":" +
                "\n\t\t\tCurrent Floor: " + currentFloor +
                "\n\t\t\tDirection: " + currentDirection +
                "\n\t\t\tDestinations: " + destinations;
    }

    /**
    Adds a floor to the ArrayList of destinations.
    @param floor The floor to be added.
    */
    public void addStop(final int floor) {
        if (!destinations.contains(floor)) {
            destinations.add(floor);
        }
    }

    /** Updates the state of the elevator. */
    public void updateState() {
        //check if arrived
        if (destinations.contains(currentFloor)) {
            destinations.removeIf(n -> (n == currentFloor));
            System.out.println("\t\tElevator #" + id + ": Arrived at floor #" + currentFloor + ".");
        }

		// take stock of assigned stops and change direction if required
        if (destinations.isEmpty()) {
            currentDirection = Direction.STOP;
        } else if ((currentDirection == Direction.UP && Collections.max(destinations) < currentFloor)) {
            currentDirection = Direction.DOWN;
			Collections.sort(destinations);
			Collections.reverse(destinations);
        } else if (currentDirection == Direction.DOWN && Collections.min(destinations) > currentFloor){
            currentDirection = Direction.UP;
			Collections.sort(destinations);
        }
		
		//Decide which way to go if the elevator is stopped and has gained a destination
		if (currentDirection == Direction.STOP && destinations.size() != 0) {
			int destinationFloor = Integer.MAX_VALUE;
			for (final Integer d : destinations) {
				if (Math.abs(currentFloor - d) < Math.abs(currentFloor - destinationFloor)) {
					destinationFloor = d;
				}
			}

			currentDirection = (destinationFloor > currentFloor ? Direction.UP : Direction.DOWN);
		}

        // update current floor
        if (currentDirection == Direction.DOWN) {
            currentFloor--;
            System.out.println("\t\tElevator #" + id + ": Moved down to floor #" + currentFloor + ".");
        } else if (currentDirection == Direction.UP) {
            currentFloor++;
            System.out.println("\t\tElevator #" + id + ": Moved up to floor #" + currentFloor + ".");
        }
    }


    /**
     Returns the current floor the elevator is on.
     @return The current floor the elevator is on.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     Returns the current direction of the elevator.
     @return The current direction of the elevator.
     */
    public Direction getDirection() {
        return currentDirection;
    }

    /**
     Returns the ID number of the elevator.
     @return The ID number of the elevator.
     */
    public int getId() {
        return id;
    }
}