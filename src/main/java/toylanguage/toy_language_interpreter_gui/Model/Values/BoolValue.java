package toylanguage.toy_language_interpreter_gui.Model.Values;

import toylanguage.toy_language_interpreter_gui.Model.Types.BoolType;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;

public class BoolValue implements IValue{
    private final boolean val;
    public BoolValue(boolean v){
        val = v;
    }
    public boolean getValue(){
        return val;
    }
    @Override
    public String toString(){
        return Boolean.toString(val);
    }
    public IType getType(){
        return new BoolType();
    }

    public IValue deepCopy(){
        return new BoolValue(val);
    }
    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof BoolValue))
            return false;
        BoolValue castValue = (BoolValue) anotherValue;
        return val == castValue.getValue();
    }
}
