package pl.edu.agh.kis.pz1;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Komenda sluzaca do rezerwacji pokoju w hitelu
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class CheckInCommand extends Command<Hotel> {
    public CheckInCommand() {
        super("checkin");
    }

    /**
     * Interaktywna metoda rezerwujaca pokoj.
     * Metoda kontroluje, czy pokoj istnieje oraz czy jest zajety.
     * @return True w przypadku powodzenia, False w przeciwnym wypadku
     */
    @Override
    public boolean execute(Hotel target) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type room number (format: f.n where f - floor, n - room number): ");
        String roomNumber = sc.nextLine();

        if(!target.getRooms().contains(roomNumber)){
            System.out.println("Room not found!");
            return false;
        }

        Room room = target.getRoom(roomNumber);
        if(room.isOccupied()){
            System.out.println("Room is occupied!");
            return false;
        }

        Person person = Person.createPerson(sc);
        room.addOccupant(person);
        room.setOccupied(true);

        target.addClient(person,room);


        if(room.getCapacity() > 1){
            for(int i = 1; i < room.getCapacity(); i++){
                System.out.println("The room has " + (room.getCapacity() - i) +" more seats. Would you like to add someone? (Y/N)");
                String answer = sc.nextLine().toLowerCase();

                if (answer.equals("y")) {
                    person = Person.createPerson(sc);
                    room.addOccupant(person);
                    target.addClient(person,room);
                }

                else if (answer.equals("n")) {
                    break;
                }

                else System.out.println("Wrong answer.");
            }
        }

        System.out.print("Type check in date (format: YYYY-MM-DD) or click enter to set current date: ");
        String checkInDate = sc.nextLine();

        while(!isValidDate(checkInDate)){
            System.out.print("\nWrong format. Try again: ");
            checkInDate = sc.nextLine();
        }

        System.out.print("\nEnter the length of stay (days): ");

        String stay = sc.nextLine();
        while(!isValidInteger(stay)){
            System.out.print("\nWrong number. Try again: ");
            stay = sc.nextLine();
        }
        int days = Integer.parseInt(stay);

        if(checkInDate.isEmpty()){
            room.setCheckInDate(LocalDate.now(),days);
        }
        else{
            room.setCheckInDate(LocalDate.parse(checkInDate), days);
        }

        System.out.print("Type additional info: ");
        String info = sc.nextLine();


        if(!info.isEmpty()){
            room.setInfo(info);
        }

        System.out.println("Check in procedure successful!");
        return true;
    }

    private static boolean isValidDate(String dateStr) {
        try {
            if(dateStr.isEmpty()){
                return true;
            }
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidInteger(String input) {
        try {
            int x = Integer.parseInt(input);
            return x > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
