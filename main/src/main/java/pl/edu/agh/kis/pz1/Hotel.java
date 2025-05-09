package pl.edu.agh.kis.pz1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import pl.edu.agh.kis.pz1.util.MyHashMap;

import java.io.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa hotelu sluzaca do zarzadzania danym hotelem
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class Hotel {
    private MyHashMap<String, Room> rooms;
    private MyHashMap<Person, Room> clients;
    private String hotelName;

    /**
     * Konstruktor tworzacy hotel w interaktywny sposob
     * Uzytkownik proszony jest o podanie danych kolejno tworzonych pokojow
     */
    public Hotel() {
        System.out.println("Room creator");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hotel name: ");
        this.hotelName = scanner.nextLine();
        rooms = new MyHashMap<>();
        System.out.println("\n1. Create a new room");
        System.out.println("2. Exit");

        String option = scanner.nextLine();
        while(!option.equals("2")) {
            System.out.print("\nRoom number (format: f.n f-floor, n-number): ");
            String roomNumber = scanner.nextLine();
            System.out.print("\nPrice number: ");
            int price = scanner.nextInt();
            System.out.print("\nRoom capacity: ");
            int capacity = scanner.nextInt();
            Room room = new Room(roomNumber, capacity, price);
            rooms.put(roomNumber,room);
            System.out.println("Room "+room.getId()+" created\n\n");
            System.out.println("1. Create a new room");
            System.out.println("2. Exit");
            scanner.nextLine();
            option = scanner.nextLine();
        }
    }

    /**
     * Konstruktor wczytujacy dane hotelu z pliku (wraz z klientami)
     *
     * @param file Nazwa hotelu
     * @throws IOException Wyjatek w razie niepowodzenia operacji zapisu/odczytu z pliku
     */
    public Hotel(String file) throws IOException {
        rooms = new MyHashMap<>();
        clients = new MyHashMap<>();
        this.hotelName = file;
        String file1 = file + "Rooms.csv";
        String file2 = file + "Persons.csv";

        try(
                Reader reader = new FileReader(file1);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord csvRecord : csvParser) {
                String id = csvRecord.get(0);
                int capacity = Integer.parseInt(csvRecord.get(1));
                int price = Integer.parseInt(csvRecord.get(2));
                Room room = new Room(id, capacity, price);
                room.setOccupied(false);

                if (csvRecord.size() > 3 && !csvRecord.get(3).isEmpty()) {
                    String reservationDateBuf = csvRecord.get(3);
                    LocalDate reservationDate = LocalDate.parse(reservationDateBuf);
                    int duration = Integer.parseInt(csvRecord.get(4));
                    room.reserveRoom(reservationDate, duration);
                    room.setOccupied(true);
                }

                if (csvRecord.size() > 5 && !csvRecord.get(5).isEmpty()) {
                    String info = csvRecord.get(5);
                    room.setInfo(info);
                }
                if (!rooms.put(id, room)) {
                    System.out.println("Can't create room " + id);
                }
            }

        }
        catch(Exception e) {
            System.out.println("Error with reading rooms!");
            e.printStackTrace();
            throw e;
        }

        try(
                Reader reader = new FileReader(file2);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord csvRecord : csvParser) {
                String id = csvRecord.get(0);
                String name = csvRecord.get(1);
                String surname = csvRecord.get(2);
                String phone = csvRecord.get(3);
                String mail = csvRecord.get(4);
                String roomNumber = csvRecord.get(5);
                Person person = new Person(id, name, surname, phone, mail);
                Room room = rooms.get(roomNumber);
                if (room == null) {
                    throw new IOException("Room with number " + roomNumber + " not found");
                }
                room.addOccupant(person);

                room.setOccupied(true);

                clients.put(person, room);
            }

        }
        catch(Exception e) {
            System.out.println("Error with reading persons!");
            throw e;
        }
    }

    /**
     *
     * @param name Nazwa pod jaka chcemy zapisac hotel
     * @throws IOException Wyjatek w razie niepowodzenia operacji zapisu/odczytu z pliku
     */
    void saveHotel(String name) throws IOException {
        String file1 = name + "Rooms.csv";
        String file2 = name + "Persons.csv";

        try(
        Writer out = new FileWriter(file1);
        CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id","Capacity","Price","reservationDate","durationDays","info"));
        ) {
            if (rooms != null) {
                for (String key : rooms.keys()) {
                    Room room = rooms.get(key);
                    if (room.isOccupied()) {
                        csvPrinter.printRecord(room.getId(), Integer.toString(room.getCapacity()), Integer.toString(room.getPrice()), room.getReservationDate().toString(), Integer.toString(room.getDurationDays()), room.getInfo());
                    } else {
                        csvPrinter.printRecord(room.getId(), Integer.toString(room.getCapacity()), Integer.toString(room.getPrice()));
                    }

                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error with saving rooms!");
            System.exit(1);
        }

        try(
        Writer out2 = new FileWriter(file2);
        CSVPrinter csvPrinter2 = new CSVPrinter(out2, CSVFormat.DEFAULT.withHeader("id","Name","Surname","PhoneNumber","Mail","Room"));
        ) {
            if (clients != null) {
                for (Person person : clients.keys()) {
                    csvPrinter2.printRecord(person.id(), person.name(), person.surname(), person.phoneNumber(), person.mail(), clients.get(person).getId());
                }
            }
        }
        catch(Exception e) {
            System.out.println("Error with saving persons! ("+e.getMessage()+")");
            System.exit(1);
        }

    }

    /**
     * Metoda zapisujaca hotel w przypadku nie pdoania nazwy (wtedy hotel jest nadpisywany)
     * @throws IOException Wyjatek w razie niepowodzenia operacji zapisu/odczytu z pliku
     */
    public void saveHotel() throws IOException {
        saveHotel(this.hotelName);
    }

    /**
     *
     * @return Lista numerow pokojow
     */
    public List<String> getRooms(){
        return rooms.keys();
    }

    /**
     *
     * @return Lista klientow bedacych w hotelu
     */
    public List<Person> getClients(){
        if(clients == null) return Collections.emptyList();
        return clients.keys();
    }

    /**
     *
     * @param roomNumber Numer pokoju
     * @return Obiekt pokoju
     */
    public Room getRoom(String roomNumber){
        return rooms.get(roomNumber);
    }

    /**
     *
     * @param person Klient hotelu
     * @param room Pokoj pod ktory osoba jest zarezerwowana
     */
    public void addClient(Person person, Room room){
        if(clients == null) {
            clients = new MyHashMap<>();
        }

        if(clients.keys().contains(person)){
            Room prevRoom = clients.get(person);
            prevRoom.getOccupants().remove(person);
            if(prevRoom.getOccupants().isEmpty()) {
                prevRoom.setOccupied(false);
                prevRoom.setInfo(null);
            }
            clients.put(person, room);
        }
        else{
            clients.put(person,room);
        }
    }

}
