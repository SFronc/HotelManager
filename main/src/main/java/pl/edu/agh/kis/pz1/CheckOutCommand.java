package pl.edu.agh.kis.pz1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

/**
 * Komenda sluzaca do wypisania osoby z hotelu
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class CheckOutCommand extends Command<Hotel> {
    public CheckOutCommand() {
        super("checkout");
    }

    /**
     * Interaktywna metoda wypisujaca osobe z pokoju.
     * Metoda wypisuje koszt pobytu oraz dane osoby na ktora wypisano rachunek zaplaty
     * @return True w przypadku powodzenia, False w przeciwnym wypadku
     */
    @Override
    public boolean execute(Hotel target) {
        System.out.print("Type room number (format: f.n where f - floor, n - room number): ");
        Scanner scanner = new Scanner(System.in);
        String roomNumber = scanner.nextLine();

        if(!target.getRooms().contains(roomNumber)){
            System.out.println("Room not found!");
            return false;
        }

        Room room = target.getRoom(roomNumber);

        if(!room.isOccupied()){
            System.out.println("Room is empty!");
            return false;
        }

        room.setOccupied(false);
        List<Person> occupants = room.getOccupants();
        List<Person> clients = target.getClients();
        long daysBetween = ChronoUnit.DAYS.between(room.getReservationDate(), LocalDate.now());
        System.out.println("Cost of room stay: " + ((daysBetween + 1)*room.getPrice()));
        System.out.print("The bill issued to: ");
        System.out.println(room.getOccupants().get(0));
        clients.removeAll(occupants);
        occupants.clear();

        return true;
    }
}
