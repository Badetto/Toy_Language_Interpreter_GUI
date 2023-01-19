package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IStack;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.BoolType;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.BoolValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;

public class IfStatement implements IStatement {
    IExpression exp;
    IStatement thenS;
    IStatement elseS;

    public IfStatement(IExpression new_exp, IStatement new_then, IStatement new_else){
        exp = new_exp;
        thenS = new_then;
        elseS = new_else;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        IValue result = exp.eval(state.getSymTable(), state.getHeap());
        if (result instanceof BoolValue){
            IStatement statement;
            if (((BoolValue) result).getValue())
                statement = thenS;
            else
                statement = elseS;
            IStack<IStatement> stack = state.getExecutionStack();
            stack.push(statement);
            state.setExecutionStack(stack);
            return null;
        }
        else{
            throw new StatementException("An if statement accepts only a boolean expression!");
        }
    }
    @Override
    public String toString(){
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }
    @Override
    public IStatement deepCopy(){
        return new IfStatement(exp.deepCopy(),thenS.deepCopy(), elseS.deepCopy());
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        IType typeExp = exp.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())){
            thenS.typeCheck(typeEnv.copy());
            elseS.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else
            throw new StatementException("The Condition of IF has not the type bool");
    }
}
