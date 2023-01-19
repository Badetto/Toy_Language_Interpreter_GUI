package toylanguage.toy_language_interpreter_gui.Model.Values;

import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Types.StringType;

public class StringValue implements IValue{
    private final String val;
    public StringValue(String v){
        val = v;
    }
    public String getValue(){
        return val;
    }
    @Override
    public String toString(){
        return val;
    }
    public IType getType(){
        return new StringType();
    }

    public IValue deepCopy(){
        return new StringValue(val);
    }
    @Override
    public boolean equals(Object anotherValue) {
        if (!(anotherValue instanceof StringValue))
            return false;
        StringValue castValue = (StringValue) anotherValue;
        return this.val.equals(castValue.getValue());
    }
}
