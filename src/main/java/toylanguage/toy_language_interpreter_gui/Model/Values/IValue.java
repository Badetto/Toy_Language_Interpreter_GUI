package toylanguage.toy_language_interpreter_gui.Model.Values;

import toylanguage.toy_language_interpreter_gui.Model.Types.IType;

public interface IValue {
    IType getType();
    IValue deepCopy();
}
