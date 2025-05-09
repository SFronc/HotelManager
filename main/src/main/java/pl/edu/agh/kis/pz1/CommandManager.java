package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.MyHashMap;

/**
 * Klasa zarzadzajaca komendami dostepnymi w programie
 *
 * @author Seweryn Fronc
 * @version 1.0
 *
 * @param <T> Typ obiektow na ktorych beda wykonywane komeny
 */
public class CommandManager<T> {
    private final MyHashMap<String,Command<T>> commands;

    public CommandManager() {
        commands = new MyHashMap<>();
    }

    /**
     * Metoda rejestrujaca nowa komede
     * @param command Obiekt komendy
     */
    public void registerCommand(Command<T> command) {
        commands.put(command.getInvokeName().toLowerCase(),command);
        System.out.println("Command " + command.getInvokeName() + " registered");
    }

    /**
     * Metoda wykonujaca dostepne komendy
     *
     * @param command Nawa komendy
     * @return Poprawnosc wykonania (True/False)
     */
    public boolean executeCommand(String command, T obj) {
        Command<T> cmd = commands.get(command);
        if(cmd == null) {
            System.out.println("Command not found");
            return false;
        }

        return cmd.execute(obj);
    }
}
