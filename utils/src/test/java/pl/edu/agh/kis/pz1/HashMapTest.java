package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.MyHashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest{
    @Test
    public void testPut(){
        MyHashMap<String,String> map = new MyHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        String out = map.get("key2");
        assertEquals("value2", out);
    }

    @Test
    public void testPut2(){
        MyHashMap<String,String> map = new MyHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key1", "value3");
        String out = map.get("key1");
        assertEquals("value3", out);
        assertEquals(2, map.keys().size());
    }

    @Test
    public void testRemove(){
        MyHashMap<String,String> map = new MyHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.remove("key2");
        String out = map.get("key2");
        assertNull(out);
    }

    @Test
    public void testContains(){
        MyHashMap<String,Double> map = new MyHashMap<>();
        map.put("keya", 3.14);
        map.put("keyb",2.0);
        assertTrue(map.contains("keya"));
        assertFalse(map.contains("keyc"));
    }
}