package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IList;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;

public class PrintStatement implements IStatement {
    IExpression exp;
    public PrintStatement(IExpression new_exp){
        exp = new_exp;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        IList<IValue> out = state.getOut();
        out.add(exp.eval(state.getSymTable(), state.getHeap()));
        state.setOut(out);
        return null;
    }
    @Override
    public String toString(){
        return "print("+exp.toString()+")";
    }
    @Override
    public IStatement deepCopy(){
        return new PrintStatement(exp.deepCopy());
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        exp.typeCheck(typeEnv);
        return typeEnv;
    }
}
