package pl.edu.agh.kis.pz1;

import java.util.Scanner;

/**
 * Komenda wyświetlajaca dane podanego pokoju (o ile istnieje)
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class ViewCommand extends Command<Hotel> {
    public ViewCommand() {
        super("view");
    }

    public boolean execute(Hotel target) {
        System.out.print("Type room number to view (format: f.n where f-floor, n-number): ");
        Scanner scanner = new Scanner(System.in);
        String roomNumber = scanner.nextLine();
        Room room = target.getRoom(roomNumber);

        if (room == null) {
            System.out.println("Room not found");
            return false;
        }

        System.out.println("\nRoom "+roomNumber);
        System.out.println("Price: "+Integer.toString(room.getPrice()));
        System.out.println("Capacity: "+Integer.toString(room.getCapacity()));
        System.out.print("Occupied: "); System.out.println(room.isOccupied());

        if(room.isOccupied()) {
            System.out.print("Reservation date: "); System.out.println(room.getReservationDate());
            System.out.print("Length of tenancy (days): "); System.out.println(room.getDurationDays());
            System.out.print("\nThe room is registered to: ");
            System.out.print(room.getOccupants().get(0));

            int i = 1;
            while(i < room.getOccupants().size()) {
                System.out.print("Occupant " + i + ": ");
                System.out.println(room.getOccupants().get(i));
                i++;
            }
        }


        if(!room.getInfo().isEmpty()) System.out.println("Additional information: " + room.getInfo() + "\n");

        return true;
    }
}
