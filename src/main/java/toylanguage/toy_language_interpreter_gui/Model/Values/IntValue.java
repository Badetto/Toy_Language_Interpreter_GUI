package toylanguage.toy_language_interpreter_gui.Model.Values;

import toylanguage.toy_language_interpreter_gui.Model.Types.IntType;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;

public class IntValue implements IValue{
    private final int val;
    public IntValue(int v){
        val = v;
    }
    public int getValue(){
        return val;
    }
    @Override
    public String toString(){
        return Integer.toString(val);
    }
    public IType getType(){
        return new IntType();
    }

    public IValue deepCopy(){
        return new IntValue(val);
    }
    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof IntValue))
            return false;
        IntValue castValue = (IntValue) anotherValue;
        return val == castValue.getValue();
    }
}
