package toylanguage.toy_language_interpreter_gui.Model.Expressions;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;

public class VariableExpression implements IExpression{
    String id;
    public VariableExpression(String new_key){
        id = new_key;
    }
    @Override
    public IValue eval(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionException, ADTException {
        return tbl.get(id);
    }
    @Override
    public String toString(){
        return id;
    }
    @Override
    public IExpression deepCopy(){
        return new VariableExpression(id);
    }
    @Override
    public IType typeCheck(IDictionary<String,IType> typeEnv) throws ExpressionException, ADTException{
        return typeEnv.get(id);
    }
}
