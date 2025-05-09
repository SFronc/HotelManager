import pl.edu.agh.kis.pz1.*;

import java.time.LocalDate;

public class CustomCommand extends Command<Hotel> {
    public CustomCommand() {
        super("cus");
    }

    @Override
    public boolean execute(Hotel hotel) {
        System.out.println("This is custom command!");
        Room room = hotel.getRoom("2.2");
        Person per = new Person("84547475888","Cezary","Abuh","+12573847345","aed@a.a");
        room.addOccupant(per);
        hotel.addClient(per,room);
        System.out.println(per);
        room.setCheckInDate(LocalDate.now(),10);
        return true;
    }

}