package pl.edu.agh.kis.pz1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

public class CheckInTest {

    @Test
    void all_correct(){
        try{
            Hotel hotel = new Hotel("Test");
            Command<Hotel> cm = new CheckInCommand();

            String simulatedInput = "3.1\n44444444444\nJan\nKowalski\n999999999\nkow@gmail.com\n2024-03-11\n15\nvip\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            cm.execute(hotel);
            System.setIn(System.in);

            Room room = hotel.getRoom("3.1");
            assertNotNull(room);

            assertTrue(room.isOccupied());
            Person per = room.getOccupants().get(0);
            assertNotNull(per);
            assertEquals("44444444444",per.id());
            assertEquals("Jan",per.name());
            assertEquals("Kowalski",per.surname());
            assertEquals("+48999999999",per.phoneNumber());
            assertEquals("kow@gmail.com",per.mail());
            assertEquals(LocalDate.parse("2024-03-11"),room.getReservationDate());
            assertEquals(15,room.getDurationDays());
            assertEquals("vip",room.getInfo());


        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void add_more_occupants(){
        try{
            Hotel hotel = new Hotel("Test");
            Command<Hotel> cm_ch_out = new CheckOutCommand(); //To free space in room 1.1
            Command<Hotel> cm = new CheckInCommand();

            String simulatedInputToClearRoom = "1.1";
            ByteArrayInputStream inputStreamToCleanRoom = new ByteArrayInputStream(simulatedInputToClearRoom.getBytes());
            System.setIn(inputStreamToCleanRoom);

            cm_ch_out.execute(hotel);

            String simulatedInput = "1.1\n44444444444\nJan\nKowalski\n999999999\nkow@gmail.com\ny\n55555555555\nAdam\nNowak\n888888888\nnow@wp.pl\n2024-03-11\n15\nvip\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            cm.execute(hotel);

            System.setIn(System.in);

            Room room = hotel.getRoom("1.1");
            assertNotNull(room);

            assertTrue(room.isOccupied());
            Person per = room.getOccupants().get(0);
            assertNotNull(per);
            assertEquals("44444444444",per.id());
            assertEquals("Jan",per.name());
            assertEquals("Kowalski",per.surname());
            assertEquals("+48999999999",per.phoneNumber());
            assertEquals("kow@gmail.com",per.mail());
            assertEquals(LocalDate.parse("2024-03-11"),room.getReservationDate());
            assertEquals(15,room.getDurationDays());
            assertEquals("vip",room.getInfo());

            per = room.getOccupants().get(1);
            assertNotNull(per);
            assertEquals("55555555555",per.id());
            assertEquals("Adam",per.name());
            assertEquals("Nowak",per.surname());
            assertEquals("now@wp.pl",per.mail());
            assertEquals("+48888888888",per.phoneNumber());


        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void room_not_found(){
        try{
            Hotel hotel = new Hotel("Test");
            Command<Hotel> cm = new CheckInCommand();

            String simulatedInput = hotel.getRooms().get(0)+"a";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            String expectedOut = "Type room number (format: f.n where f - floor, n - room number): Room not found!\n";
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));


            cm.execute(hotel);

            System.setIn(System.in);
            System.setOut(System.out);

            assertEquals(expectedOut.replace(System.lineSeparator(),"\n"),outputStream.toString().replace(System.lineSeparator(),"\n"));


        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void room_is_occupied(){
        try{
            Hotel hotel = new Hotel("Test");
            Command<Hotel> cm = new CheckInCommand();

            String simulatedInput = hotel.getRooms().get(0);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            String expectedOut = "Type room number (format: f.n where f - floor, n - room number): Room is occupied!\n";
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));


            cm.execute(hotel);

            System.setIn(System.in);
            System.setOut(System.out);

            assertEquals(expectedOut.replace(System.lineSeparator(),"\n"),outputStream.toString().replace(System.lineSeparator(),"\n"));


        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }
}
