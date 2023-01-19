package toylanguage.toy_language_interpreter_gui.View;

import toylanguage.toy_language_interpreter_gui.Controller.Controller;
import toylanguage.toy_language_interpreter_gui.Model.Adts.*;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Types.*;
import toylanguage.toy_language_interpreter_gui.Model.Values.*;
import toylanguage.toy_language_interpreter_gui.Model.Statements.*;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.*;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.Repository.IRepository;
import toylanguage.toy_language_interpreter_gui.Repository.Repository;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class View {
    private void runStatement(IStatement statement) throws StatementException, ExpressionException, ADTException, IOException, InterruptedException {
        IStack<IStatement> executionStack = new MyStack<>();
        IDictionary<String, IValue> symbolTable = new MyDictionary<>();
        IList<IValue> output = new MyList<>();
        IDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        IHeap heap = new MyHeap();
        ProgramState state = new ProgramState(executionStack, symbolTable, output, fileTable, statement, heap);

        IRepository repository = new Repository(state, "log.txt");
        Controller controller = new Controller(repository);

        System.out.println("Do you want to display the steps?[Y/N]");
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        if (Objects.equals(option, "Y"))
            controller.setDisplayFlag(true);

        controller.allSteps();
        System.out.println("Result is" + state.getOut().toString());
    }
    private void program1() throws StatementException, ExpressionException, ADTException, IOException, InterruptedException {
        IStatement ex1 = new CompoundStatement(new DeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        runStatement(ex1);
    }

    private void program2() throws StatementException, ExpressionException, ADTException, IOException, InterruptedException {
        IStatement ex2 = new CompoundStatement(new DeclarationStatement("a",new IntType()),
                new CompoundStatement(new DeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(1,new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression(3,new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression(1,new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        runStatement(ex2);
    }

    private void program3() throws StatementException, ExpressionException, ADTException, IOException, InterruptedException {
        IStatement ex3 = new CompoundStatement(new DeclarationStatement("a", new BoolType()),
                new CompoundStatement(new DeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        runStatement(ex3);
    }
    private void printMenu() {
        System.out.println("MENU: ");
        System.out.println("0. Exit.");
        System.out.println("1. Run the first program: int v; v=2; Print(v)");
        System.out.println("2. Run the second program: int a; int b; a=2+3*5; b=a+1; Print(b)");
        System.out.println("3. Run the third program: bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)");
        System.out.println("Choose an option: ");
    }
    public void start(){
        while (true){
            try{
                printMenu();
                Scanner input = new Scanner(System.in);
                int option = input.nextInt();
                if (option == 0){
                    break;
                }
                else if (option == 1){
                    program1();
                }
                else if (option == 2){
                    program2();
                }
                else if (option == 3){
                    program3();
                }
                else{
                    System.out.println("Invalid input!");
                }
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
