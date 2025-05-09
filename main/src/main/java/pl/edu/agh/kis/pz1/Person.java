package pl.edu.agh.kis.pz1;


import java.time.LocalDateTime;
import java.util.Scanner;


/**
 * Klasa reprezentująca osobę
 * Osoba definiowana jest przez wartości takie jak id, name, surname, phoneNumber, mail
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class Person {
    private final String id; //PESEL
    private final String name;
    private final String surname;
    private String phoneNumber;
    private String mail;

    /**
     * Statyczna interaktywna metoda do utworzenia nowej osoby
     * Metoda zapewnia podstawową poprawność danych takich jak numer telefonu, poprawny PESEL i mail
     * W przypadku braku podania id, generuje się domyśny identyfikator
     * W razie niepdoania liczby kierunkowej numeru, domyślnie wstawia się +48
     * @return obiekt klasy Person (konkretna osoba)
     */
    public static Person createPerson(Scanner sc2) {
        System.out.println("Creating Person...");
        //Scanner sc2 = new Scanner(System.in);
        String data;
        String id;
        String name;
        String surname;
        String phoneNumber;
        String mail;
        System.out.print("PESEL (skip if PESEL not definied): ");
        data = sc2.nextLine();
        while (data.length() != 11 && !data.isEmpty() && !data.matches("\\d{11}")) {
            System.out.print("\nWrong number, please try again: ");
            data = sc2.nextLine();
        }

        if(data.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            id = "D"+String.format("%04d%02d%02d%02d%02d%02d",now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond()); //D means "default"
        }
        else {
            id = data;
        }

        System.out.print("NAME: ");
        name = sc2.nextLine();

        while (name.isEmpty()){
            System.out.print("\nWrong name, please try again: ");
            name = sc2.nextLine();
        }

        System.out.print("SURNAME: ");

        surname = sc2.nextLine();

        while (surname.isEmpty()){
            System.out.print("\nWrong surname, please try again: ");
            surname = sc2.nextLine();
        }


        System.out.print("PHONE NUMBER: ");
        phoneNumber = "";

        while((data = sc2.nextLine()).length() != 9 && !data.isEmpty() && data.length() != 12 && !data.matches("\\d{9}")){
            System.out.print("\nWrong number, please try again: ");
        }

        if(!data.isEmpty()) {
            if (data.charAt(0) == '+') {
                phoneNumber = data;
            } else {
                phoneNumber = "+48" + data;
            }
        }

        System.out.print("MAIL: ");
        while(!((data = sc2.nextLine()).contains("@"))) {
            System.out.print("\nWrong email, please try again: ");
        }
        mail = data;

        return new Person(id, name, surname, phoneNumber, mail);

    }

    /**
     * Konstruktor tworzący osobę w sposób nieinteraktywny
     * @param id Identyfiaktor osoby (PESEL)
     * @param name Imie osoby
     * @param surname Nazwisko osoby
     * @param phoneNumber Numer telefonu
     * @param mail Mail kontaktowy osoby
     */
    public Person(String id, String name, String surname, String phoneNumber, String mail) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    /**
     * Getter identyfikatora osoby (PESEL)
     * @return Id osoby (PESEL)
     */
    public String id() {
        return id;
    }

    /**
     * Getter imienia osoby
     * @return Imie osoby
     */
    public String name() {
        return name;
    }

    /**
     * Getter
     * @return Nazwisko osoby
     */
    public String surname() {
        return surname;
    }

    /**
     * Getter
     * @return Numer tel. osoby
     */
    public String phoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter
     * @return Mail
     */
    public String mail() {
        return mail;
    }

    /**
     * Metoda zmieniajaca numer tel osoby
     * @param phoneNumber Nowy numer tel.
     */
    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Metoda zwracajaca Hashcode osoby
     * @return Hash code
     */
    public int hashCode(){
        return id.hashCode();
    }

    /**
     * Metoda porownujaca dwie osoby
     * @param obj Obiekt do porownania
     * @return True jesli osoby sa rowne (take same ID), False w przeciwnym wypadku
     */
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj instanceof Person){
            Person person = (Person)obj;
            return id.equals(person.id());
        }
        return false;
    }

    /**
     * Metoda zwaracajace sformatowane dane osoby
     * @return Ciag znakow opisujacych osobe
     */
    @Override
    public String toString(){
        return name + " " + surname + "\n" + "Id: " + id + "\nPhone Number: " + phoneNumber + "\nMail: " + mail + "\n";
    }

}
