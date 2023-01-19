package toylanguage.toy_language_interpreter_gui.Model.Adts;

import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import java.util.HashMap;
import java.util.Set;

public class MyHeap implements IHeap{
    HashMap<Integer, IValue> heap;
    Integer freeLocationValue;

    public MyHeap(){
        this.heap = new HashMap<>();
        freeLocationValue = 1;
    }

    public int newLocationValue() {
        freeLocationValue += 1;
        while (freeLocationValue == 0 || heap.containsKey(freeLocationValue))
            freeLocationValue += 1;
        return freeLocationValue;
    }
    @Override
    public int getFreeLocationValue(){
        return freeLocationValue;
    }
    @Override
    public HashMap<Integer, IValue> getHeap() {
        return heap;
    }
    @Override
    public void setHeap(HashMap<Integer, IValue> newMap) {
        this.heap = newMap;
    }
    @Override
    public int add(IValue value) {
        heap.put(freeLocationValue, value);
        Integer toReturn = freeLocationValue;
        freeLocationValue = newLocationValue();
        return toReturn;
    }
    @Override
    public void update(Integer position, IValue value) throws ADTException {
        if (!heap.containsKey(position))
            throw new ADTException(position.toString() + " is not present in the heap");
        heap.put(position, value);
    }
    @Override
    public IValue get(Integer position) throws ADTException {
        if (!heap.containsKey(position))
            throw new ADTException(position.toString() + " is not present in the heap");
        return heap.get(position);
    }
    @Override
    public boolean containsKey(Integer position) {
        return this.heap.containsKey(position);
    }
    @Override
    public void remove(Integer key) throws ADTException {
        if (!containsKey(key))
            throw new ADTException(key + " is not defined.");
        freeLocationValue = key;
        this.heap.remove(key);
    }
    @Override
    public Set<Integer> keySet() {
        return heap.keySet();
    }
    @Override
    public String toString() {
        return this.heap.toString();
    }
    @Override
    public HashMap<Integer, IValue> getContent() {
        return heap;
    }
}
