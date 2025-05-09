package pl.edu.agh.kis.pz1;

import java.util.Scanner;

/**
 * Komenda zapisuje dane hotela i loaktorów do plików.
 * Użytkownik proszony jest o podanie nazwy kotelu, pliki są w formacie:
 * nazwaRooms.csv - plik z konfiguracją pokojów
 * nazwaPersons.csv - plik z klientami
 * gdzie nazwa to podana nazwa hotelu
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class SaveCommand extends Command<Hotel> {
    public SaveCommand() {
        super("save");
    }

    @Override
    public boolean execute(Hotel target) {
        String name;
        System.out.print("Type new hotel name (click enter if you want to keep the current name)");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        try{
            if(name.isEmpty()){
                target.saveHotel();
            }
            else{
                target.saveHotel(name);
            }
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
