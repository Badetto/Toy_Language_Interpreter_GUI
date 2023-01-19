package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException;
    IStatement deepCopy();
    IDictionary<String,IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException;
}
