package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.*;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.MyException.ExpressionException;
import toylanguage.toy_language_interpreter_gui.MyException.StatementException;

import java.util.Map;

public class ForkStatement implements IStatement{
    private final IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException{
        IStack<IStatement> newStack = new MyStack<>();
        newStack.push(statement);
        IDictionary<String, IValue> newSymTable = new MyDictionary<>();
        for (Map.Entry<String, IValue> entry: state.getSymTable().getContent().entrySet()) {
            newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
        }
        return new ProgramState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap());
    }
    @Override
    public IStatement deepCopy(){
        return new ForkStatement(statement.deepCopy());
    }
    @Override
    public String toString(){
        return String.format("Fork(%s)", statement.toString());
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        statement.typeCheck(typeEnv.copy());
        return typeEnv;
    }
}
