package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLoaderTest {

    @Test
    void loadTest(){
        String workingDirectory = System.getProperty("user.dir");
        System.out.println(workingDirectory);
        Set<Command<String>> cmds = new CommandLoader<String>().loadCommands(workingDirectory);

        assertNotNull(cmds);
        assertFalse(cmds.isEmpty());
        assertEquals(cmds.size(), 1);
        Command<String> cmd = cmds.iterator().next();
        assertNotNull(cmd);
        assertEquals(cmd.getInvokeName(),"cus");
        assertTrue(cmd.execute("a"));

        String expectedOut = "Abc321";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        cmd.execute(expectedOut);

        assertEquals(baos.toString().replace(System.lineSeparator(), "\n"),expectedOut.replace(System.lineSeparator(), "\n"));


        System.setOut(System.out);



    }
}
