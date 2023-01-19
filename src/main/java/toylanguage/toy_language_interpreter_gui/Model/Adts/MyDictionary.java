package toylanguage.toy_language_interpreter_gui.Model.Adts;

import java.util.HashMap;
import java.util.Set;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;

public class MyDictionary<K, V> implements IDictionary<K, V>{
    private final HashMap<K, V> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<>();
    }
    @Override
    public void put(K key, V value){
        dictionary.put(key, value);
    }
    @Override
    public V get(K key) throws ADTException{
        if (!containsKey(key)){
            throw new ADTException(key.toString() + " is not defined");
        }
        return dictionary.get(key);
    }
    @Override
    public boolean containsKey(K id) {
        return dictionary.containsKey(id);
    }
    @Override
    public Set<K> keySet() {
        return dictionary.keySet();
    }
    @Override
    public void remove(K key) throws ADTException{
        if (!containsKey(key)){
            throw new ADTException(key.toString() + " is not defined");
        }
        dictionary.remove(key);
    }
    @Override
    public HashMap<K, V> getContent() {
        return dictionary;
    }
    @Override
    public IDictionary<K, V> copy() throws ADTException{
        IDictionary<K, V> toReturn = new MyDictionary<>();
        for (K key : keySet())
            toReturn.put(key, get(key));
        return toReturn;
    }
    @Override
    public String toString(){
        return dictionary.toString();
    }
}
