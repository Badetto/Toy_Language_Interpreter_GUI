package toylanguage.toy_language_interpreter_gui.Model.Adts;

import toylanguage.toy_language_interpreter_gui.MyException.ADTException;

import java.util.List;

public interface IList<T> {
    public List<T> getList();
    void add(T v);
    int size();
    boolean contains(T v);
    boolean remove(T v) throws ADTException;
}
