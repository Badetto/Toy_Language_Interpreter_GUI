package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.RefType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.MyException.ExpressionException;
import toylanguage.toy_language_interpreter_gui.MyException.StatementException;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.RefValue;

public class NewStatement implements  IStatement{
    private final String variableName;
    private final IExpression expression;
    public NewStatement(String variableName, IExpression expression){
        this.variableName = variableName;
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException{
        IDictionary<String, IValue> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        if (!symTable.containsKey(variableName))
            throw new StatementException(variableName + "is not in symTable");
        IValue variableValue = symTable.get(variableName);
        if (!(variableValue.getType() instanceof RefType))
            throw new StatementException(variableName + " is not RefType");
        IValue evaluated = expression.eval(symTable, heap);
        IType locationType = ((RefValue)variableValue).getLocationType();
        if (!locationType.equals(evaluated.getType()))
            throw new StatementException(variableName + " not of " + evaluated.getType());
        int newPosition = heap.add(evaluated);
        symTable.put(variableName, new RefValue(newPosition, locationType));
        state.setSymTable(symTable);
        state.setHeap(heap);
        return null;
    }
    @Override
    public IStatement deepCopy() {
        return new NewStatement(variableName, expression.deepCopy());
    }
    @Override
    public String toString() {
        return String.format("New(%s, %s)", variableName, expression);
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        IType typeVar = typeEnv.get(variableName);
        IType typeExp = expression.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp))){
            return typeEnv;
        }
        else
            throw new StatementException("NEW statement: right hand side and left hand side have different types");
    }

}
