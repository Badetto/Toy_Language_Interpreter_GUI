package toylanguage.toy_language_interpreter_gui.Model.Expressions;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;

public class ValueExpression implements IExpression {
    IValue value;
    public ValueExpression(IValue new_value){
        value = new_value;
    }
    @Override
    public IValue eval(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionException, ADTException {
        return value;
    }
    @Override
    public String toString(){
        return value.toString();
    }
    @Override
    public IExpression deepCopy(){
        return new ValueExpression(value.deepCopy());
    }
    @Override
    public IType typeCheck(IDictionary<String,IType> typeEnv) throws ExpressionException, ADTException{
        return value.getType();
    }
}
