package toylanguage.toy_language_interpreter_gui.Model.Types;

import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.IntValue;

public class IntType implements IType {
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;
    }
    public IValue getDefault(){
        return new IntValue(0);
    }
    @Override
    public String toString(){
        return "int";
    }

    public IType deepCopy(){
        return new IntType();
    }
}
