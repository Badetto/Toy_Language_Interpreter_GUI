package toylanguage.toy_language_interpreter_gui.Model.Statements;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IStack;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.IExpression;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.IType;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.MyException.ExpressionException;
import toylanguage.toy_language_interpreter_gui.MyException.StatementException;
import toylanguage.toy_language_interpreter_gui.Model.Types.BoolType;
import toylanguage.toy_language_interpreter_gui.Model.Values.BoolValue;

public class WhileStatement implements IStatement{
    private final IExpression expression;
    private final IStatement statement;
    public WhileStatement(IExpression expression, IStatement statement){
        this.expression = expression;
        this.statement = statement;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, ADTException{
        IValue value = expression.eval(state.getSymTable(), state.getHeap());
        IStack<IStatement> stack =  state.getExecutionStack();
            if (!value.getType().equals(new BoolType()))
                throw new StatementException(value + " is not of BoolType");
            BoolValue boolValue = (BoolValue) value;
            if (boolValue.getValue()) {
                stack.push(this);
                stack.push(statement);
            }
        return null;
    }
    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }
    @Override
    public String toString() {
        return String.format("while(%s){%s}", expression, statement);
    }
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String,IType> typeEnv) throws StatementException, ExpressionException, ADTException{
        IType typeExpr = expression.typeCheck(typeEnv);
        if (typeExpr.equals(new BoolType())) {
            statement.typeCheck(typeEnv.copy());
            return typeEnv;
        } else
            throw new StatementException("The condition of WHILE does not have the type Bool.");
    }
}
