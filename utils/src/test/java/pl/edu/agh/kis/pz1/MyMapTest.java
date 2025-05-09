package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.MyHashMap;
import org.junit.jupiter.api.Test;
import pl.edu.agh.kis.pz1.util.MyMap;

import static org.junit.jupiter.api.Assertions.*;

public class MyMapTest {

    @Test
    void maptest(){
        MyMap<String,Integer> map = new MyMap<>();
        map.put("a", 1);
        map.put("b", 2);

        assertEquals(2, map.keys().size());
        assertEquals(1, map.get("a"));

        map.remove("a");

        assertNull(map.get("a"));

        assertTrue(map.contains("b"));
        assertFalse(map.contains("c"));
        assertFalse(map.remove("d"));
    }
}
