package toylanguage.toy_language_interpreter_gui.Model.Expressions;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Types.IntType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.IntValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;

public class ArithmeticExpression implements IExpression {
    IExpression exp1;
    IExpression exp2;
    int op; // 1-plus, 2-minus, 3-star, 4-divide
    public ArithmeticExpression(int new_op, IExpression new_exp1, IExpression new_exp2){
        exp1=new_exp1;
        exp2=new_exp2;
        op=new_op;
    }
    @Override
    public IValue eval(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionException, ADTException {
        IValue v1,v2;
        v1 = exp1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())){
            v2 = exp2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1,n2;
                n1=i1.getValue();
                n2=i2.getValue();
                if (op == 1){
                    return new IntValue(n1+n2);
                }
                else if (op == 2){
                    return new IntValue(n1-n2);
                }
                else if (op==3){
                    return new IntValue(n1*n2);
                }
                else if (op==4){
                    if (n2==0) throw new ExpressionException("Division by zero");
                    else
                        return new IntValue(n1/n2);
                }
            }
            else throw new ExpressionException("Second operand is not an integer");
        }
        else throw new ExpressionException("First operand is not an integer");
        return null;
    }
    @Override
    public String toString(){
        if (op==1)
            return exp1.toString() + " + " + exp2.toString();
        else if (op==2)
            return exp1.toString() + " - " + exp2.toString();
        else if (op==3)
            return exp1.toString() + " * " + exp2.toString();
        return exp1.toString() + " / " + exp2.toString();
    }
    @Override
    public IExpression deepCopy(){
        return new ArithmeticExpression(op, exp1.deepCopy(), exp2.deepCopy());
    }
    @Override
    public IType typeCheck(IDictionary<String,IType> typeEnv) throws ExpressionException, ADTException{
        IType typ1, typ2;
        typ1 = exp1.typeCheck(typeEnv);
        typ2 = exp2.typeCheck(typeEnv);
        if (typ1.equals(new IntType())){
            if (typ2.equals(new IntType())){
                return new IntType();
            }
            else
                throw new ExpressionException("Second operand is not an integer!");
        }
        else
            throw new ExpressionException("First operand is not an integer!");
    }
}
