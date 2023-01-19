package toylanguage.toy_language_interpreter_gui.Model.Adts;

import toylanguage.toy_language_interpreter_gui.MyException.ADTException;

import java.util.HashMap;
import java.util.Set;
public interface IDictionary<T1, T2> {
    void put(T1 v1, T2 v2);
    T2 get(T1 id) throws ADTException;
    boolean containsKey(T1 id);
    Set<T1> keySet();
    void remove(T1 key) throws ADTException;
    HashMap<T1, T2> getContent();
    IDictionary<T1, T2> copy() throws ADTException;

}
