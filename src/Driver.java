import java.util.Random;

public class Driver {
    public static void main(final String[] args) {
		final Building building = new Building(10, 3);
		final Random random = new Random();

        for (int i = 0 ; i < 100 ; i++) {
			System.out.println("\n\nTick #" + i + ":");

			System.out.println("\tEvents:");
			if (random.nextBoolean()) {
				building.requestElevator(random.nextInt(building.getFloors() + 1), Direction.values()[random.nextInt(2)]);
			}
			building.updateState();

			System.out.println("\tElevators:");
			for (final Elevator temp : building.getElevators()) {
				System.out.println(temp.toString());
			}
		}
    }
}