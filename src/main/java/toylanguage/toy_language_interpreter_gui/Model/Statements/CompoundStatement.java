package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IStack;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;

public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement second;
    public CompoundStatement(IStatement new_first, IStatement new_second){
        first = new_first;
        second = new_second;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }
    @Override
    public String toString(){
        return "(" + first.toString() + ";" + second.toString() + ")";
    }
    @Override
    public IStatement deepCopy(){
        return new CompoundStatement(first.deepCopy(), second.deepCopy());
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        return second.typeCheck(first.typeCheck(typeEnv));
    }
}
