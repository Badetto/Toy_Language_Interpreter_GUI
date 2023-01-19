package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IStack;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;

public class AssignmentStatement implements IStatement {
    private final String id;
    private final IExpression exp;
    public AssignmentStatement(String new_id, IExpression new_exp){
        id = new_id;
        exp = new_exp;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException {
        IStack<IStatement> stk = state.getExecutionStack();
        IDictionary<String, IValue> symTbl = state.getSymTable();
        if (symTbl.containsKey(id)){
            IValue val = exp.eval(symTbl, state.getHeap());
            IType typeId = (symTbl.get(id)).getType();
            if (val.getType().equals(typeId))
                symTbl.put(id, val);
            else
                throw new StatementException("declared type of variable" + id + " and type of the assigned expression do not match");
        }
        else
            throw new StatementException("the used variable" +id+" was not declared before");

        return null;
    }
    @Override
    public String toString(){
        return id+"="+exp.toString();
    }
    @Override
    public IStatement deepCopy(){
        return new AssignmentStatement(id, exp.deepCopy());
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        IType typeVar = typeEnv.get(id);
        IType typeExp = exp.typeCheck(typeEnv);
        if (typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new StatementException("Assigment: right hand side and left hand side have different types");
    }

}
