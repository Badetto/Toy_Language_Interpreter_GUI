package toylanguage.toy_language_interpreter_gui.Model.Expressions;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Types.RefType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.RefValue;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.MyException.ExpressionException;

public class ReadHeapExpression implements IExpression{
    private final IExpression expression;

    public ReadHeapExpression(IExpression expression){
        this.expression = expression;
    }
    @Override
    public IValue eval(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionException, ADTException{
        IValue value = expression.eval(tbl, heap);
        if (!(value instanceof RefValue refValue))
            throw new ExpressionException(value + " is not of RefType");
        return heap.get(refValue.getAddress());
    }
    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }
    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }
    @Override
    public IType typeCheck(IDictionary<String,IType> typeEnv) throws ExpressionException, ADTException{
        IType typ = expression.typeCheck(typeEnv);
        if (typ instanceof RefType){
            RefType reft = (RefType) typ;
            return reft.getInner();
        }
        else
            throw new ExpressionException("The ReadHeap argument is not a RefType.");
    }
}
