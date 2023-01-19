package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Types.StringType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.StringValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import java.io.BufferedReader;
import java.io.IOException;


public class CloseReadFile implements IStatement{
    private final IExpression exp;
    public CloseReadFile(IExpression new_exp){
        exp = new_exp;
    }
    @Override
    public ProgramState execute(ProgramState state) throws ExpressionException, StatementException, ADTException{
        IValue value = exp.eval(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType()))
            throw new StatementException(exp.toString() + " does not evaluate to StringValue");
        StringValue fileName = (StringValue) value;
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.containsKey(fileName.getValue()))
            throw new StatementException(String.format("%s is not present in the FileTable", value));
        BufferedReader br = fileTable.get(fileName.getValue());
        try {
            br.close();
        } catch (IOException e) {
            throw new StatementException(String.format("Unexpected error in closing %s", value));
        }
        fileTable.remove(fileName.getValue());
        state.setFileTable(fileTable);
        return null;
    }
    @Override
    public IStatement deepCopy(){
        return new CloseReadFile(exp.deepCopy());
    }
    @Override
    public String toString(){
        return "Close Read File " + exp.toString();
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        if (exp.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new StatementException("CloseReadFile requires a string expression.");
    }

}
