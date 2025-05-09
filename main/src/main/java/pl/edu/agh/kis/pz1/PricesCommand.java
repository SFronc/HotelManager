package pl.edu.agh.kis.pz1;


import java.util.List;

/**
 * Komenda wypisująca ceny pokoi
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class PricesCommand extends Command<Hotel> {
    public PricesCommand() {
        super("prices");
    }

    /**
     * Metoda wykonująca komemndę
     * @return Powodzenie
     */
    @Override
    public boolean execute(Hotel target) {
        List<String> rooms = target.getRooms();
        if(rooms.isEmpty()){
            System.out.println("No rooms found");
            return false;
        }

        for(String number : rooms) {
            System.out.println("Room number: "+number);
            Room room = target.getRoom(number);
            if(room == null){
                System.out.println("Room not found");
                return false;
            }
            System.out.print("Price: ");
            System.out.println(room.getPrice());
            System.out.println();
        }

        return true;
    }
}
