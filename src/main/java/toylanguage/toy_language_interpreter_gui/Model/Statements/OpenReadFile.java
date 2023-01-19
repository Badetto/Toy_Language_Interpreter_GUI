package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Types.StringType;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.MyException.StatementException;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFile implements IStatement{
    private final IExpression exp;
    public OpenReadFile(IExpression new_exp){
        exp = new_exp;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        IValue value = exp.eval(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new StringType())){
            StringValue fileName = (StringValue) value;
            IDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.containsKey(fileName.getValue())){
                BufferedReader buff_read;
                try{
                    buff_read = new BufferedReader(new FileReader(fileName.getValue()));
                } catch(IOException e){
                    throw new StatementException(fileName.getValue() + " could not be opened");
                }
                fileTable.put(fileName.getValue(), buff_read);
                state.setFileTable(fileTable);
            }
            else{
                throw new StatementException(exp + " is already opened");
            }
        }
        else{
            throw new StatementException(exp + " is not of StringType");
        }
        return null;
    }
    @Override
    public IStatement deepCopy(){
        return new OpenReadFile(exp.deepCopy());
    }
    @Override
    public String toString(){
        return "Open Read File: " + exp.toString();
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        if (exp.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new StatementException("OpenReadFile requires a string expression.");
    }
}
