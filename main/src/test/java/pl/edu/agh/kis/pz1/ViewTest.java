package pl.edu.agh.kis.pz1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ViewTest {

    @Test
    void viewtest(){
        try{
            Hotel hotel = new Hotel("Test");
            Command<Hotel> cm = new ViewCommand();
            String simulatedInput = "1.1\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            PrintStream originalOut = System.out;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            String expected = "Type room number to view (format: f.n where f-floor, n-number): \nRoom 1.1\nPrice: 1200\nCapacity: 2\nOccupied: true\nReservation date: 2024-11-20\nLength of tenancy (days): 12\n\nThe room is registered to: Adam Nowak\nId: 34854577777\nPhone Number: +12777777777\nMail: a@a.a\n";

            cm.execute(hotel);

            assertEquals(expected.replace(System.lineSeparator(), "\n"), baos.toString().replace(System.lineSeparator(), "\n"));


            System.setIn(System.in);
            System.setOut(originalOut);
        }
        catch(Exception e){
            fail(e.getMessage());
        }

    }
}
