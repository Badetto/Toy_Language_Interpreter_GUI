package toylanguage.toy_language_interpreter_gui.Model;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IHeap;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IList;
import toylanguage.toy_language_interpreter_gui.Model.Adts.IStack;
import toylanguage.toy_language_interpreter_gui.Model.Statements.IStatement;
import toylanguage.toy_language_interpreter_gui.Model.Values.IValue;
import toylanguage.toy_language_interpreter_gui.MyException.*;

import java.io.BufferedReader;
import java.util.List;

public class ProgramState {
    private IStack<IStatement> executionStack;
    private IDictionary<String, IValue> symTable;
    private IDictionary<String, BufferedReader> fileTable;
    private IHeap heap;
    private IList<IValue> out;
    IStatement originalProgram;
    private int id;
    private static int lastId = 0;
    public ProgramState(IStack<IStatement> stack, IDictionary<String, IValue> dict, IList<IValue> list, IDictionary<String, BufferedReader> fTable, IStatement prg, IHeap heap){
        executionStack = stack;
        symTable = dict;
        out = list;
        fileTable = fTable;
        this.heap = heap;
        originalProgram = prg.deepCopy();
        executionStack.push(prg);
        id = setId();
    }
    public ProgramState(IStack<IStatement> stack, IDictionary<String, IValue> dict, IList<IValue> list, IDictionary<String, BufferedReader> fTable,  IHeap heap){
        executionStack = stack;
        symTable = dict;
        out = list;
        fileTable = fTable;
        this.heap = heap;
        id = setId();
    }
    public IStack<IStatement> getExecutionStack(){
        return executionStack;
    }
    public IDictionary<String, IValue> getSymTable(){
        return symTable;
    }
    public IList<IValue> getOut(){
        return out;
    }
    public IDictionary<String, BufferedReader> getFileTable(){
        return fileTable;
    }
    public IHeap getHeap(){
        return heap;
    }
    public static synchronized int setId(){
        lastId++;
        return lastId;
    }
    public void setExecutionStack(IStack<IStatement> new_executionStack){
        executionStack = new_executionStack;
    }
    public void setSymTable(IDictionary<String, IValue> new_symTable){
        symTable = new_symTable;
    }
    public void setOut(IList<IValue> new_out){
        out = new_out;
    }
    public void setFileTable(IDictionary<String, BufferedReader> newFileTable){
        fileTable = newFileTable;
    }
    public void setHeap(IHeap heap){
        this.heap = heap;
    }
    public boolean isNotCompleted(){
        return executionStack.isEmpty();
    }
    public ProgramState oneStep() throws StatementException, ExpressionException, ADTException {
        if (executionStack.isEmpty()){
            throw new StatementException("Program state stack is empty!");
        }
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }
    @Override
    public String toString(){
        return "Id: " + id + "\nExecution stack: \n" + executionStack.toString() +
                "\nSymbol table: \n" + symTable.toString() +
                "\nOutput list: \n" + out.toString() +
                "\nHeap memory: \n" + heap.toString();
    }

    public String executionStackToString(){
        StringBuilder executionStackStringBuilder = new StringBuilder();
        List<IStatement> stack = executionStack.getReversed();
        for (IStatement statement:stack){
            executionStackStringBuilder.append(statement.toString()).append("\n");
        }
        return executionStackStringBuilder.toString();
    }

    public String symTableToString() throws StatementException, ExpressionException, ADTException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.get(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (IValue elem: out.getList()) {
            outStringBuilder.append(elem).append("\n");
        }
        return outStringBuilder.toString();
    }
    public String fileTableToString(){
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key: fileTable.keySet()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }
    public String heapToString() throws StatementException, ExpressionException, ADTException{
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key: heap.keySet()) {
            heapStringBuilder.append(String.format("%d -> %s\n", key, heap.get(key)));
        }
        return heapStringBuilder.toString();
    }
    public String programStateToString() throws StatementException, ExpressionException, ADTException {
        return "Id: " + id + "\nExecution stack: \n" + executionStackToString() + "Symbol table: \n" +
                symTableToString() + "Output list: \n" + outToString() + "File table:\n" + fileTableToString() +
                "Heap memory:\n" + heapToString();
    }

}
