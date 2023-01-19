package toylanguage.toy_language_interpreter_gui.Model.Types;

import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.StringValue;

public class StringType implements IType{
    @Override
    public boolean equals(Object another){
        return another instanceof StringType;
    }
    public IValue getDefault(){
        return new StringValue("");
    }
    @Override
    public String toString(){
        return "string";
    }

    public IType deepCopy(){
        return new StringType();
    }
}
