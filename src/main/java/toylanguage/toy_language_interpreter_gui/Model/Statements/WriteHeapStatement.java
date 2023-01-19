package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.Model.Values.RefValue;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.MyException.ExpressionException;
import toylanguage.toy_language_interpreter_gui.MyException.StatementException;
import toylanguage.toy_language_interpreter_gui.Model.Types.RefType;

public class WriteHeapStatement implements IStatement{
    private final String variableName;
    private final IExpression expression;
    public WriteHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException{
        IDictionary<String, IValue> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        if (!symTable.containsKey(variableName))
            throw new StatementException(variableName + " not present in the symTable");
        IValue value = symTable.get(variableName);
        if (!(value instanceof RefValue))
            throw new StatementException(value + " not of RefType");
        RefValue refValue = (RefValue) value;
        IValue evaluated = expression.eval(symTable, heap);
        if (!evaluated.getType().equals(refValue.getLocationType()))
            throw new StatementException(evaluated + " not of " + refValue.getLocationType());
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(variableName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("WriteHeap(%s, %s)", variableName, expression);
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        if (typeEnv.get(variableName).equals(new RefType(expression.typeCheck(typeEnv))))
            return typeEnv;
        else
            throw new StatementException("WriteHeap: right hand side and left hand side have different types.");
    }

}
