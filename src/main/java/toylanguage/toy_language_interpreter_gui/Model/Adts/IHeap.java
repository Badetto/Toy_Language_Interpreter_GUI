package toylanguage.toy_language_interpreter_gui.Model.Adts;

import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import java.util.HashMap;
import java.util.Set;

public interface IHeap {
    int getFreeLocationValue();
    HashMap<Integer, IValue> getHeap();
    void setHeap(HashMap<Integer, IValue> newMap);
    int add(IValue value);
    void update(Integer position, IValue value) throws ADTException;
    IValue get(Integer position) throws ADTException;
    boolean containsKey(Integer position);
    void remove(Integer key) throws ADTException;
    Set<Integer> keySet();
    HashMap<Integer, IValue> getContent();
}
