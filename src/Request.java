/**
This class models a request for an elevator, created when no
elevators are stopped or travelling in the desired direction.
@author Samuel Beaulieu
*/
public class Request {
    /** The floor the passenger is waiting on. */
    private final int waitFloor;

    /** The direction the passenger wishes to go. */
    private final Direction waitDirection;

    /**
    Constructs a new request for an elevator given a floor and the direction
    the passenger wishes to travel.
    @param waitFloor The floor the passenger is waiting on.
    @param waitDirection The direction the passenger wishes to go.
    */
    public Request(int waitFloor, Direction waitDirection) {
        this.waitFloor = waitFloor;
        this.waitDirection = waitDirection;
    }

    /**
    Returns the floor the passenger is waiting on.
    @return The floor the passenger is waiting on.
    */
    public int getWaitFloor() {
        return waitFloor;
    }

    /**
    Returns the direction the passenger wishes to go.
    @return The direction the passenger wishes to go.
    */
    public Direction getWaitDirection() {
        return waitDirection;
    }
}