package pl.edu.agh.kis.pz1;

import java.util.Scanner;

/**
 * Komenda obslugujaca wyjscie z programu
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class ExitCommand extends Command<Hotel> {
    public ExitCommand() {
        super("exit");
    }

    /**
     * Metoda przed wyjsciem upewnia sie czy uzytkownik na pewno chce wyjsc i ostrzega o mozliwej utracie niezapisanych danych
     *
     * @return Poprawnosc wykonania
     */
    @Override
    public boolean execute(Hotel target) {
        System.out.println("Are you sure to exit? Changes will not be saved (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        option = option.toLowerCase();
        if (option.equals("y")) {
            System.exit(0);
        }
        return false;
    }
}
