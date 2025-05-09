package pl.edu.agh.kis.pz1.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

interface Map<K, V> {
    /**
     * Dodanie elementu do mapy pod podanym kluczem.
     Jeśli podany klucz istnieje to metoda powinna podmienić wartość.
     * @param key klucz (nie null)
     * @param value wartość kryjącą się pod kluczem (nie null)
     * @return true jeśli się uda dodać element, false jeśli nie
     */
    boolean put(K key, V value);
    /**
     * Usunięcie podanego klucza oraz wartości, która jest przechowywana pod tym
     kluczem.
     * @param key klucz do usunięcia
     * @return true jeśli uda się usunać klucz, false w przeciwnym przypadku
     */
    boolean remove(K key);
    /**
     * Zwraca wartość pod podanym kluczem lub null jeśli nie ma podanego klucza.
     * @param key klucz (nie ull)
     * @return wartość pod kluczem lub null jeśli nie ma wartości dla podanego klucza
     */
    V get(K key);
    /**
     * Zwraca listę kluczy
     * @return java.util.List lista kluczy
     */
    List<K> keys();
    /**
     * Sprawdza czy podany klucz istnieje w mapie.
     * @param key wartość klucza do sprawdzenia.
     * @return true jeśli klucz istnieje.
     */
    boolean contains(K key);
}

public class MyMap<K,V> implements Map<K,V>{
    private List<Data<K,V>> list;


    public static class Data<K,V>{
        private K key;
        private V value;

        public Data(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }

        public V setValue(V value){
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object obj){
            if(this == obj) return true;
            if(obj == null || obj.getClass() != getClass()) return false;
            Data<K,V> data = (Data<K,V>) obj;
            return Objects.equals(key, data.key) && Objects.equals(value, data.value);
        }

        public int hashCode(){
            return Objects.hash(key, value);
        }
    }
    public MyMap() {
        list = new ArrayList<>();
    }

    @Override
    public boolean put(K key, V value){
        if(key == null) {
            return false;
        }

        for(Data<K,V> data : list){
            if(data.getKey().equals(key)){
                data.setValue(value);
                return true;
            }
        }

        list.add(new Data<>(key, value));
        return true;
    }

    @Override
    public boolean remove(K key) {
        for(Data<K,V> data : list){
            if(data.getKey().equals(key)){
                list.remove(data);
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(K key) {
        for(Data<K,V> data : list){
            if(data.getKey().equals(key)){
                return data.getValue();
            }
        }

        return null;
    }

    @Override
    public List<K> keys() {
        List<K> keys = new ArrayList<>();
        for(Data<K,V> data : list){
            keys.add(data.getKey());
        }

        return keys;
    }

    @Override
    public boolean contains(K key) {
        for(Data<K,V> data : list){
            if(data.getKey().equals(key)){
                return true;
            }
        }
        return false;
    }
}


