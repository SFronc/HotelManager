package pl.edu.agh.kis.pz1.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * HashMapa implementująca interfejs mapy
 * Mapa wykorzystuje metodę rozwiązywania kolizji przez utworzenie listy pod danym kluczem
 *
 * @param <K> Typ klucza
 * @param <V> Typ wartosc
 *
 * @author Seweryn Fronc
 * @version 1.0
 */
public class MyHashMap<K,V> implements Map<K,V>{
    private final int size;
    private final List<K> keys;
    private final List<MyHashMap.Data<K,V>>[] data;

    /**
     * Konstruktor oczekujący rozmiar mapy
     * @param size Rozmiar HashMapy
     */
    public MyHashMap(int size) {
        this.size = size;
        data = (List<MyHashMap.Data<K,V>>[]) new List[size];
        keys = new ArrayList<>();
    }

    /**
     * Kontruktor domyślny
     */
    public MyHashMap() {
        this(100000);
    }

    /**
     * Metoda dodająca nowe dane do mapy
     *
     * @param key klucz
     * @param value wartość kryjącą się pod kluczem
     * @return Powodzenie operacji
     */
    @Override
    public boolean put(K key, V value) {
        if(key == null) return false;

        int hash = (key.hashCode() % size + size) % size;

        List<MyHashMap.Data<K,V>> x = data[hash];
        if(x == null) {
            x = new ArrayList<>();
            x.add(new MyHashMap.Data<>(key, value));
            data[hash] = x;
            keys.add(key);
            return true;
        }
        else{
            boolean found = false;
            for(MyHashMap.Data<K,V> d : x){
                if(d.getKey().equals(key)){
                    d.setValue(value);
                    found = true;
                }
            }

            if(!found){
                x.add(new MyHashMap.Data<>(key, value));
                keys.add(key);
            }

            data[hash] = x;
            return true;
        }
    }

    /**
     * Metoda usuwająca dane z mapy
     *
     * @param key klucz do usunięcia
     * @return Powodzenie
     */
    @Override
    public boolean remove(K key) {
        int hash = (key.hashCode() % size + size) % size;
        List<MyHashMap.Data<K,V>> x = data[hash];
        if(x != null){
            for(MyHashMap.Data<K,V> d : x){
                if(d.getKey().equals(key)){
                    x.remove(d);
                    keys.remove(key);
                    break;
                }
            }
            if(x.isEmpty()){
                data[hash] = null;
            }
            else{
                data[hash] = x;
            }
        }
        return false;
    }

    /**
     * Metoda zwracająca zmapowane dane
     *
     * @param key klucz
     * @return Wartość (obiekt) przechowywany pod podanym kluczem
     */
    @Override
    public V get(K key) {
        int hash = (key.hashCode() % size + size) % size;
        List<MyHashMap.Data<K,V>> x = data[hash];
        if(x == null) {
            return null;
        }
        else{
            for(MyHashMap.Data<K,V> d : x){
                if(d.getKey().equals(key)){
                    return d.getValue();
                }
            }
            return null;
        }
    }

    /**
     *
     * @return Lsita kluczy
     */
    @Override
    public List<K> keys() {
        return keys;
    }

    /**
     *
     * @param key wartość klucza do sprawdzenia.
     * @return True jeśli klucz zostanie znaleziony, inaczej False
     */
    @Override
    public boolean contains(K key) {
        return keys.contains(key);
    }

    /**
     * Dane przechowywane w Mapie
     * Klasa ta została dodana w celu rozwiązania konfliktów jeśli dany klucz będzie zmakowany na tą samą wartość
     *
     * @param <K> Typ klucza
     * @param <V> Typ wartości
     */
    public static class Data<K,V>{
        private final K key;
        private V value;

        /**
         * Konstruktor
         *
         * @param key Klucz
         * @param value Wartość
         */
        public Data(K key, V value){
            this.key = key;
            this.value = value;
        }

        /**
         * Getter
         *
         * @return Klucz
         */
        public K getKey(){
            return key;
        }

        /**
         * Getter
         *
         * @return Wartość
         */
        public V getValue(){
            return value;
        }

        /**
         * Setter
         *
         * @param value Nowa wartość
         * @return Poprzednia wartość przechowywana w tym miejscu
         */
        public V setValue(V value){
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /**
         *
         * @param obj Obiekt do porównania
         * @return Prawda jeśli obiekty są takie same, inaczej Fałsz
         */
        @Override
        public boolean equals(Object obj){
            if(this == obj) return true;
            if(obj == null || obj.getClass() != getClass()) return false;
            MyHashMap.Data<K,V> data = (MyHashMap.Data<K,V>) obj;
            return Objects.equals(key, data.key) && Objects.equals(value, data.value);
        }

        /**
         * @return Wartość Hash
         */
        @Override
        public int hashCode(){
            return Objects.hash(key, value);
        }
    }
}
