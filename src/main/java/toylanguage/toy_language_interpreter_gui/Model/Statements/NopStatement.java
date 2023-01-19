package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Types.StringType;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.MyException.ExpressionException;
import toylanguage.toy_language_interpreter_gui.MyException.StatementException;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state){
        return null;
    }
    @Override
    public IStatement deepCopy(){
        return new NopStatement();
    }
    @Override
    public String toString(){
        return "NopStatement";
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException {
        return typeEnv;
    }
}
