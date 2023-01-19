package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;

public class DeclarationStatement implements IStatement {
    String name;
    IType type;
    public DeclarationStatement(String new_name, IType new_type){
        name = new_name;
        type = new_type;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException{
        IDictionary<String, IValue> symTable = state.getSymTable();
        if (symTable.containsKey(name)) {
            throw new StatementException("Variable " + name + " already exists in the symTable.");
        }
        symTable.put(name, type.getDefault());
        state.setSymTable(symTable);
        return null;
    }
    @Override
    public String toString(){
        return name + "=" + type.toString();
    }
    @Override
    public IStatement deepCopy(){
        return new DeclarationStatement(name, type.deepCopy());
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        typeEnv.put(name, type);
        return typeEnv;
    }
}
