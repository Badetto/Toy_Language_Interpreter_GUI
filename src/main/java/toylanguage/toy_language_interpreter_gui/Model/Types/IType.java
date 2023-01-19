package toylanguage.toy_language_interpreter_gui.Model.Types;

import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;

public interface IType {
    IValue getDefault();
    IType deepCopy();
}
