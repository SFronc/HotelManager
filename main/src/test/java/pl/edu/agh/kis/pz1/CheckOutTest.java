package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class CheckOutTest {
    @Test
    public void test_occupied() {
        try{
            Hotel hotel = new Hotel("Test");
            Command<Hotel> cm = new CheckOutCommand();

            assertNotNull(hotel.getRoom("1.1"));
            assertTrue(hotel.getRoom("1.1").isOccupied());
            assertEquals(hotel.getRoom("1.1").getOccupants().size(), 1);
            Person person = hotel.getRoom("1.1").getOccupants().get(0);

            String simulatedInput = "1.1\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            cm.execute(hotel);
            System.setIn(System.in);

            Room room = hotel.getRoom("1.1");
            assertNotNull(room);

            assertFalse(room.isOccupied());
            assertEquals(room.getOccupants().size(), 0);
            assertFalse(hotel.getClients().contains(person));
        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }
}
