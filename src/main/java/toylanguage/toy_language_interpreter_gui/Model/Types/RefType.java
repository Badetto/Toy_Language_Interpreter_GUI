package toylanguage.toy_language_interpreter_gui.Model.Types;

import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.RefValue;

public class RefType implements IType {
    private final IType inner;
    public RefType(IType inner) {
        this.inner = inner;
    }
    public IType getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object another){
        if (another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else
            return false;
    }

    @Override
    public IValue getDefault(){
        return new RefValue(0, inner);
    }

    @Override
    public IType deepCopy(){
        return new RefType(inner.deepCopy());
    }

    @Override
    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }
}
