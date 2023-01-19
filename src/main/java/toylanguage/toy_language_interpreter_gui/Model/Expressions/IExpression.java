package toylanguage.toy_language_interpreter_gui.Model.Expressions;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;

public interface IExpression {
    IValue eval(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionException, ADTException;
    IExpression deepCopy();
    IType typeCheck(IDictionary<String,IType> typeEnv) throws ExpressionException, ADTException;
}
