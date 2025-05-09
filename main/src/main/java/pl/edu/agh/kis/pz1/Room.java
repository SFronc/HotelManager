package pl.edu.agh.kis.pz1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Klasa reprezentująca pokój hotelu
 * Składa się z takich danych jak id (numer w formacie f.n gdzie f - piętro, n - numer pokoju), ceny, pojemności, dodatkowej informacji, daty rezerwacji i jej długości
 * Klasa zawiera również listę lokatorów
 */
public class Room {
    private String id;
    private int price;
    private final int capacity;
    private String info;
    private LocalDate reservationDate;
    private int durationDays;


    private List<Person> occupants;
    private boolean occupied = false;

    /**
     *
     * @param number Numer pokoju (w formacie f.n gdzie f - piętro, n - numer pokoju)
     * @param capacity Pojemność
     * @param price Cena pokoju
     */
    public Room(String number, int capacity, int price) {
        this.id = number;
        this.capacity = capacity;
        this.price = price;
        this.occupants = null;
        info = "";
    }

    /**
     * @return Numer piętra w formacie int
     */
    public int getFloor() {
        return Integer.parseInt(this.id.substring(0, this.id.indexOf('.')));
    }

    /**
     * @return Numer pokoju w formacie int
     */
    public int getNumber() {
        return Integer.parseInt(this.id.substring(this.id.indexOf('.') + 1));
    }

    /**
     * Getter
     * @return Cena
     */
    public int getPrice() {
        return price;
    }

    /**
     * Metoda ustawia nową cenę pokoju
     * @param price Nowa cena
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Getter
     * @return Pojemność pokoju
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Getter
     * @return Lista lokatorów
     */
    public List<Person> getOccupants() {
        return occupants;
    }

    /**
     * Setter dodający listę nowych lokatorów do pokoju
     * @param occupants Lista nowych lokatorów
     * @return Powodzenie operacji
     */
    public boolean setOccupants(List<Person> occupants) {
        if(occupants.size() > (this.capacity - 1)) return false;
        this.occupants = occupants;
        return true;
    }

    /**
     * Metoda sprawdzająca zajętość pokoju
     * @return True/False
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Metoda ustawiająca zajętość pokoju
     * @param occupied Wartość (True - zajęty, False - wolny)
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Dodaje jedną osobę do pokoju
     * @param person Nowa osoba
     * @return Powodzenei operacji
     */
    public boolean addOccupant(Person person) {
        if(this.occupants == null) this.occupants = new ArrayList<>();
        if(this.occupants.size() >= (this.capacity)) return false;
        this.occupants.add(person);
        this.setOccupied(true);
        return true;
    }

    /**
     * Usunięcie lokatora. Jeśli usunięta zostanie pierwsza osoba, pokój staje się zarejestrowany na kolejną osobę na liście
     * @param person Osoba do usinięcia
     * @return Powodzenie operacji
     */
    public boolean removeOccupant(Person person) {
        if(this.occupants.contains(person)) return false;
        this.occupants.remove(person);
        return true;
    }

    /**
     * @return Hash
     */
    public int hashCode(){
        return Objects.hash(id);
    }

    /**
     * Metoda sprawdza czy pokoje sątakie same (czy majątaki sam numer)
     * @param obj Pokój do porównania
     * @return True jeśli mają taki sam numer, ianczej False
     */
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj instanceof Room){
            Room room = (Room)obj;
            return id.equals(room.id);
        }
        return false;
    }

    /**
     * Metoda rezerwująca pokój
     * @param reservationDate Data rezerwacji
     * @param durationDays Długość pobytu
     */
    public void reserveRoom(LocalDate reservationDate, int durationDays) {
        this.reservationDate = reservationDate;
        this.durationDays = durationDays;
    }

    /**
     * Zwaraca datę rezerwacji
     * @return Data rezerwacji
     */
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    /**
     * Getter
     * @return Ilość dni na które zarezerwowany jest pokój
     */
    public int getDurationDays() {
        return durationDays;
    }

    /**
     * Getter
     * @return Dodatkowe informacje
     */
    public String getInfo() {
        return info;
    }

    /**
     * Setter
     * @param info Nowa informacja
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Getter
     * @return id (numer) pokoju
     */
    public String getId() {
        return id;
    }

    /**
     * Metoda ustawiająca datę rezerwacji i długość
     * @param checkInDate Data wpisu
     * @param durationDays Planowana długosć pobytu
     */
    public void setCheckInDate(LocalDate checkInDate, int durationDays) {
        this.reservationDate = checkInDate;
        this.durationDays = durationDays;
    }

}
