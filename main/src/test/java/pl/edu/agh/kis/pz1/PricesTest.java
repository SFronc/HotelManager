package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PricesTest {

    @Test
    void test(){
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try{
            Hotel hotel = new Hotel("Test");
            Command<Hotel> cmd = new PricesCommand();
            System.setOut(new PrintStream(baos));
            cmd.execute(hotel);

            String out = baos.toString();
            String expected = "Room number: 1.1\nPrice: 1200\n\nRoom number: 1.2\nPrice: 1400\n\nRoom number: 2.1\nPrice: 2000\n\nRoom number: 2.2\nPrice: 2000\n\nRoom number: 3.1\nPrice: 2500\n\n";

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
