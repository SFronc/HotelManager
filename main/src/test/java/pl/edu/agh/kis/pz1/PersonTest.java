package pl.edu.agh.kis.pz1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class PersonTest {

    @Test
    public void inputTest(){
        Person person1 = new Person("34934345333","Nam1","Sur1","343433333","a@a.a");
        Person person2 = new Person("34934345444","Nam1","Sur1","343433333","a@a.a");
        Person person3 = new Person("34934345333","Nam1","Sur1","343433333","a@a.a");

        assertNotEquals(person1,person2);
        assertEquals(person1,person3);
        assertEquals("34934345333",person3.id());
        assertEquals("Nam1",person3.name());
        assertEquals("Sur1",person3.surname());
        assertEquals("343433333",person3.phoneNumber());
        assertEquals("a@a.a",person2.mail());

        assertEquals("Nam1 Sur1\nId: 34934345333\nPhone Number: 343433333\nMail: a@a.a\n", person3.toString());
        person3.changePhoneNumber("111111111");
        assertEquals("Nam1 Sur1\nId: 34934345333\nPhone Number: 111111111\nMail: a@a.a\n",person3.toString());




        Person person4 = new Person("34934345333","Nam2","Sur2","434345633","b@b.b");
        assertEquals(person3.hashCode(),person4.hashCode());
    }
}
