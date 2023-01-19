package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.Types.*;
import toylanguage.toy_language_interpreter_gui.Model.Values.*;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.MyException.StatementException;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement{
    private final IExpression exp;
    private final String varName;
    public ReadFile(IExpression new_exp, String new_varName){
        exp = new_exp;
        varName = new_varName;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        IDictionary<String, IValue> symTable = state.getSymTable();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();

        if (symTable.containsKey(varName)) {
            IValue value = symTable.get(varName);
            if (value.getType().equals(new IntType())) {
                value = exp.eval(symTable, state.getHeap());
                if (value.getType().equals(new StringType())) {
                    StringValue castValue = (StringValue) value;
                    if (fileTable.containsKey(castValue.getValue())) {
                        BufferedReader br = fileTable.get(castValue.getValue());
                        try {
                            String line = br.readLine();
                            if (line == null)
                                line = "0";
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        } catch (IOException e) {
                            throw new StatementException("Could not read from file: " + castValue);
                        }
                    } else {
                        throw new StatementException("The file table does not contain: " + castValue);
                    }
                } else {
                    throw new StatementException(value + "%s does not evaluate to StringType");
                }
            } else {
                throw new StatementException(value+ " is not of type IntType");
            }
        } else {
            throw new StatementException(varName + " is not present in the symTable.");
        }
        return null;
    }
    @Override
    public IStatement deepCopy(){
        return new ReadFile(exp.deepCopy(),varName);
    }
    @Override
    public String toString(){
        return "ReadFile(" + exp.toString() + "," + varName + ")";
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        if (exp.typeCheck(typeEnv).equals(new StringType()))
            if (typeEnv.get(varName).equals(new IntType()))
                return typeEnv;
            else
                throw new StatementException("ReadFile requires an int as its variable parameter.");
        else
            throw new StatementException("ReadFile requires a string as es expression parameter.");
    }
}
