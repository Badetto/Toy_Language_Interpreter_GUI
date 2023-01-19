package toylanguage.toy_language_interpreter_gui.Model.Expressions;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.Model.Types.BoolType;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Types.IntType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.BoolValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;

public class LogicExpression implements IExpression{
    IExpression exp1;
    IExpression exp2;
    int op; // 1-and, 2-or
    public LogicExpression(IExpression new_ex1, IExpression new_ex2, int new_op){
        exp1 = new_ex1;
        exp2 = new_ex2;
        op = new_op;
    }
    @Override
    public IValue eval(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionException, ADTException {
        IValue v1,v2;
        v1 = exp1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())){
            v2 = exp2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())){
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean n1,n2;
                n1=b1.getValue();
                n2=b2.getValue();
                if (op == 1){
                    return new BoolValue(n1 && n2);
                }
                else if (op == 2){
                    return new BoolValue(n1 || n2);
                }
            }
            else throw new ExpressionException("Second operand is not a boolean");
        }
        else throw new ExpressionException("First operand is not a boolean");
        return null;
    }
    @Override
    public String toString(){
        if (op==1)
            return exp1.toString() + " && " + exp2.toString();
        return exp1.toString() + " || " + exp2.toString();
    }
    @Override
    public IExpression deepCopy(){
        return new LogicExpression(exp1.deepCopy(), exp2.deepCopy(), op);
    }
    @Override
    public IType typeCheck(IDictionary<String,IType> typeEnv) throws ExpressionException, ADTException{
        IType typ1, typ2;
        typ1 = exp1.typeCheck(typeEnv);
        typ2 = exp2.typeCheck(typeEnv);
        if (typ1.equals(new BoolType())){
            if (typ2.equals(new BoolType())){
                return new BoolType();
            }
            else
                throw new ExpressionException("Second operand is not a boolean!");
        }
        else
            throw new ExpressionException("First operand is not a boolean!");
    }
}
