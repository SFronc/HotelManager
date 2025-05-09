package pl.edu.agh.kis.pz1;



import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 * Klasa głóna obsługująca program, zapewnia interakcję między użytkownikiem a programem
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class Main {
    /**
     * Metoda prosi o wybranie opcji załadowania hotelu (z pliku lub utworzenie ręczne), następnie wykonuje zadane polecenia
     * @param args Argumenty wywołania
     */
    public static void main( String[] args ) {
        System.out.println( "===Hotel manager 1.0===" );
        System.out.println("Choose option ");
        System.out.println("1. Load data from keyboard");
        System.out.println("2. Load data from file");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        while(!option.equals("1") && !option.equals("2")) {
            System.out.println("Wrong input. Try again ");
            System.out.println("Choose option ");
            System.out.println("1. Load data from keyboard");
            System.out.println("2. Load data from file");
            option = scanner.nextLine();
        }
        Hotel hotel = null;
        switch(option) {
            case "1":
                hotel = new Hotel();
            break;
            case "2":
                System.out.print("Hotel name: ");
                String fileName = scanner.nextLine();
                try {
                    hotel = new Hotel(fileName);
                }
                catch (IOException e) {
                    System.exit(1);
                }
            break;
            default:
                System.out.println("Wrong input.");
                System.exit(1);
            break;
        }

        System.out.println("Hotel loaded successfully\n");

        CommandManager<Hotel> commandManager = Main.registerCommands(hotel);

        while(true) {
            System.out.print("Type command: ");

            String command = scanner.nextLine();
            command = command.toLowerCase();

            if(commandManager.executeCommand(command,hotel)){
                System.out.println("Command successfully executed!");
                if(command.equals("exit")) {
                    break;
                }
            }
            else {
                System.out.println("Command failed!");
            }

        }
    }

    public static CommandManager<Hotel> registerCommands(Hotel obj){
        CommandManager<Hotel> commandManager = new CommandManager<>();

        commandManager.registerCommand(new PricesCommand());
        commandManager.registerCommand(new ExitCommand());
        commandManager.registerCommand(new ViewCommand());
        commandManager.registerCommand(new SaveCommand());
        commandManager.registerCommand(new CheckInCommand());
        commandManager.registerCommand(new CheckOutCommand());
        commandManager.registerCommand(new ListCommand());

        System.out.print("Load custom commands? (Y/N): ");
        Scanner sc1 = new Scanner(System.in);
        String option = sc1.nextLine().toLowerCase();
        while(!option.equals("y") && !option.equals("n")) {
            System.out.println("Wrong input. Try again ");
            option = sc1.nextLine().toLowerCase();
        }



        if(option.equals("y")) {
            System.out.print("\nEnter the command directory: ");
            option = sc1.nextLine();
            Set<Command<Hotel>> loaded_cmds = new CommandLoader<Hotel>().loadCommands(option);
            if(loaded_cmds != null) {
                for(Command<Hotel> cmd : loaded_cmds) {
                    commandManager.registerCommand(cmd);
                }
            }
        }

        return commandManager;
    }
}
