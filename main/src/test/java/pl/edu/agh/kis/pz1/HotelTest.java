package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.Test;
import static org.junit.Assume.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {

    @Test
    public void createHotelFromKeyboard(){

        String input = "Test\n1\n1.1\n1000\n2\n1\n2.1\n2000\n1\n1\n2.2\n1500\n4\n2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        Hotel hotel = new Hotel();

        assertNotNull(hotel);
        assertEquals(Collections.emptyList(),hotel.getClients());
        assertEquals(3, hotel.getRooms().size());

        assumeNotNull(hotel.getRooms());

        List<String> numbers = hotel.getRooms();
        assertEquals("1.1", numbers.get(0));
        assertEquals("2.1", numbers.get(1));
        assertEquals("2.2", numbers.get(2));

        Room room = hotel.getRoom(numbers.get(0));
        assertNotNull(room);
        assertFalse(room.isOccupied());
        assertEquals(1000,room.getPrice());
        assertEquals(2,room.getCapacity());

        room = hotel.getRoom(numbers.get(2));
        assertNotNull(room);
        assertFalse(room.isOccupied());
        assertEquals(1500,room.getPrice());
        assertEquals(4,room.getCapacity());

        System.setIn(System.in);
    }

    @Test
    public void readFromFile(){
        IOException exception = assertThrows(IOException.class, () -> {
            Hotel hotel = new Hotel("dere");
        });

        try {
            Hotel hotel = new Hotel("Test");

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void writeToFile(){
        Hotel hotel;
        try {
            hotel = new Hotel("Test");
            hotel.saveHotel("Test");
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}
