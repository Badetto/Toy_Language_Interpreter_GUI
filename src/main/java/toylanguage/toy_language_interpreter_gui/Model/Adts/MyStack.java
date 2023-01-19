package toylanguage.toy_language_interpreter_gui.Model.Adts;

import toylanguage.toy_language_interpreter_gui.MyException.ADTException;

import java.util.Arrays;
import java.util.Stack;
import java.util.List;
import java.util.Collections;

public class MyStack<T> implements IStack<T> {
    private final Stack<T> stack;
    public MyStack(){
        stack = new Stack<>();
    }
    @Override
    public T pop() throws ADTException {
        if (stack.isEmpty())
            throw new ADTException("Stack is empty");
        return stack.pop();
    }
    @Override
    public T peek() throws ADTException {
        if (stack.isEmpty())
            throw new ADTException("Stack is empty");
        return stack.peek();
    }
    @Override
    public void push(T v) {
        stack.push(v);
    }
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    @Override
    public Stack<T> getStack() {
        return stack;
    }
    @Override
    public String toString(){
        return stack.toString();
    }
    @Override
    public List<T> getReversed(){
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }
}
