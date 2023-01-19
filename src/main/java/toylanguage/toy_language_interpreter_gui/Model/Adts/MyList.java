package toylanguage.toy_language_interpreter_gui.Model.Adts;

import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import java.util.LinkedList;
import java.util.List;

public class MyList<T> implements  IList<T>{
    private final List<T> list;
    public MyList(){
        list = new LinkedList<>();
    }
    @Override
    public List<T> getList(){
        return list;
    }
    @Override
    public void add(T v){
        list.add(v);
    }
    @Override
    public int size(){
        return list.size();
    }
    @Override
    public boolean contains(T v){
        return list.contains(v);
    }
    @Override
    public boolean remove(T v) throws ADTException {
        if (list.isEmpty())
            throw new ADTException("The list is empty!");
        return list.remove(v);
    }
    @Override
    public String toString(){
        return list.toString();
    }
}
