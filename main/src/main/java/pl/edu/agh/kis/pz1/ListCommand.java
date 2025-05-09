package pl.edu.agh.kis.pz1;

/**
 * Komenda wypisująca wszystkie pokoje z ich lokatorami
 */
public class ListCommand extends Command<Hotel>{
    public ListCommand() {
        super("list");
    }

    /**
     * Komenda wypisująca dane
     * @return Powodzenie
     */
    @Override
    public boolean execute(Hotel target){
        if(target.getRooms() == null) return false;

        Room room;
        for(String number : target.getRooms()){
            System.out.println("Room " + number);
            room = target.getRoom(number);
            if(room.isOccupied()) {
                System.out.println("Occupancy: " + room.getOccupants().size() + "/" + room.getCapacity());
                System.out.print("Renter: ");
                System.out.println(room.getOccupants().get(0));

                if (room.getOccupants().size() > 1) {
                    for (int i = 1; i < room.getOccupants().size(); i++) {
                        System.out.println("Occupant " + i);
                        System.out.println(room.getOccupants().get(i));
                    }
                }
                System.out.print("Planned check-out date: ");
                System.out.println(room.getReservationDate().plusDays(room.getDurationDays()));
            }
            else{
                System.out.println("Occupancy: 0/" + room.getCapacity());
            }
            System.out.println();
        }

        return true;
    }
}
