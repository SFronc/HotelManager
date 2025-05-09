package pl.edu.agh.kis.pz1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ListTest {

    @Test
    public void listTest() {

        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            Hotel hotel = new Hotel("Test");
            Command<Hotel> cmd = new ListCommand();
            System.setOut(new PrintStream(baos));
            cmd.execute(hotel);

            String out = baos.toString();
            String expected = "Room 1.1\nOccupancy: 1/2\nRenter: Adam Nowak\nId: 34854577777\nPhone Number: +12777777777\nMail: a@a.a\n\nPlanned check-out date: 2024-12-02\n\nRoom 1.2\nOccupancy: 1/1\nRenter: Jan Kowalski\nId: 77777777777\nPhone Number: +12999999999\nMail: b@a.a\n\nPlanned check-out date: 2024-11-29\n\nRoom 2.1\nOccupancy: 1/1\nRenter: a Buha\nId: D20241124024828\nPhone Number: +48444444444\nMail: a@a.a\n\nPlanned check-out date: 2024-12-13\n\nRoom 2.2\nOccupancy: 1/2\nRenter: Cezary Abuh\nId: 84547475888\nPhone Number: +12573847345\nMail: aed@a.a\n\nPlanned check-out date: 2024-12-04\n\nRoom 3.1\nOccupancy: 0/1\n\n";

            assertEquals(expected.replace(System.lineSeparator(), "\n"),out.replace(System.lineSeparator(), "\n"));
        }
        catch(Exception e){
            fail(e.getMessage());
        }
        finally{
            System.setOut(originalOut);
        }
    }
}
