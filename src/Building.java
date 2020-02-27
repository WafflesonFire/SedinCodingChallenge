import java.util.List;
import java.util.ArrayList;

public class Building {
    /** The number of floors in the building. */
    private final int floors;

    /** The list of elevators in the building. */
    private final List<Elevator> elevators;

    /** An ArrayList of pending requests. */
    private final ArrayList<Request> queue = new ArrayList<>();

    /**
    Constructs a new building using a number of floors and a number of elevators.
    This constructor also calls the elevator constructor the appropriate number of times.
    @param floors The number of floors in the building.
    @param numElevators The number of elevators in the building.
    */
    public Building(final int floors, final int numElevators) {
        this.floors = floors;

        elevators = new ArrayList<>();
        for (int i = 0 ; i < numElevators ; i++) {
            elevators.add(new Elevator(i));
        }
    }

    /**
    Requests an elevator to a certain floor.
    @param passengerFloor The floor that the elevator was called on.
    @param direction The desired direction that the passenger wishes to go.
    */
    public void requestElevator(final int passengerFloor, final Direction direction) {
        boolean available = false;
        Elevator selectedElevator = this.elevators.get(0);

        for (final Elevator temp : elevators) {
            //Tests whether an elevator is going in the right direction
            //For example, if the elevator is on floor 6 going down to floor 3 and someone presses the up button on floor 4, the elevator should not stop on floor 4
            //Until arriving at floor 3
            boolean match = (passengerFloor < temp.getCurrentFloor() && direction == Direction.DOWN);
            match |= (passengerFloor > temp.getCurrentFloor() && direction == Direction.UP);
            match |= temp.getDirection() == Direction.STOP;

            //Checks which elevator is the best for the current request by checking absolute distance and the above matching system
            final int elevatorFloor = temp.getCurrentFloor();
            if (Math.abs(elevatorFloor - passengerFloor) <= Math.abs(selectedElevator.getCurrentFloor() - passengerFloor)) {
                if(match) {
                    selectedElevator = temp;
                    available = true;
                }

            }
        }

        //Requests the elevator if a best match was found or creates a pending request otherwise
        if (available) {
            selectedElevator.addStop(passengerFloor);
        } else {
            for (final Request request : queue) {
                boolean addToQueue = request.getWaitFloor() != passengerFloor;
                addToQueue &= request.getWaitDirection() != direction;

                if (addToQueue) {
                    queue.add(new Request(passengerFloor, direction));
                }
            }
        }

        System.out.println("\t\tElevator #" + selectedElevator.getId() + ": Requested at floor #" + passengerFloor + ".");
    }

    /**
    Updates the state of the building by calling each elevator to update their
    states.
    */
    public void updateState() {
        //Update the elevators
        for (final Elevator e : this.elevators) {
            e.updateState();
        }

        //Update the queue
        if (queue.size() != 0) {
            for (final Request r : this.queue) {
                requestElevator(r.getWaitFloor(), r.getWaitDirection());
            }
        }
    }

    /**
     Returns the number of floors in the building.
     @return The number of floors in the building.
     */
    public int getFloors() {
        return floors;
    }

    /**
     Returns the list of elevators in the building.
     @return The list of elevators in the building.
     */
    public List<Elevator> getElevators() {
        return elevators;
    }
}