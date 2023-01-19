package toylanguage.toy_language_interpreter_gui.Model.Adts;

import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import java.util.Stack;
import java.util.List;

public interface IStack<T>{
    T pop() throws ADTException;
    T peek() throws ADTException;
    void push(T v);
    boolean isEmpty();
    Stack<T> getStack();
    public List<T> getReversed();
}
