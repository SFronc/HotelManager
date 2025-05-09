import pl.edu.agh.kis.pz1.*;

import java.time.LocalDate;

public class CustomCommand extends Command<String> {
    public CustomCommand() {
        super("cus");
    }

    @Override
    public boolean execute(String s) {
        System.out.print(s);
        return true;
    }

}