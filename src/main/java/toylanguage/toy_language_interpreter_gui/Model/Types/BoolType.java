package toylanguage.toy_language_interpreter_gui.Model.Types;

import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.BoolValue;

public class BoolType implements IType {
    @Override
    public boolean equals(Object another){
        return another instanceof BoolType;
    }
    public IValue getDefault(){
        return new BoolValue(false);
    }
    @Override
    public String toString(){
        return "bool";
    }

    public IType deepCopy(){
        return new BoolType();
    }
}
