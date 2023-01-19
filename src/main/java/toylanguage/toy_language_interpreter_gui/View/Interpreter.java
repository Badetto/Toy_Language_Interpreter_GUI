package toylanguage.toy_language_interpreter_gui.View;

import toylanguage.toy_language_interpreter_gui.Controller.Controller;
import toylanguage.toy_language_interpreter_gui.Model.Adts.*;
import toylanguage.toy_language_interpreter_gui.Model.Values.*;
import toylanguage.toy_language_interpreter_gui.Model.Types.*;
import toylanguage.toy_language_interpreter_gui.Model.Statements.*;
import toylanguage.toy_language_interpreter_gui.Model.Expressions.*;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;
import toylanguage.toy_language_interpreter_gui.MyException.ExpressionException;
import toylanguage.toy_language_interpreter_gui.MyException.StatementException;
import toylanguage.toy_language_interpreter_gui.Repository.IRepository;
import toylanguage.toy_language_interpreter_gui.Repository.Repository;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class Interpreter {
    public static void main(String[] args) {
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", " exit"));

        IStatement ex1 = new CompoundStatement(new DeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        try {
            ex1.typeCheck(new MyDictionary<>());
            ProgramState prg1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex1, new MyHeap());
            IRepository repo1 = new Repository(prg1, "log1.txt");
            Controller controller1 = new Controller(repo1);
            menu.addCommand(new RunExampleCommand("1", " " + ex1, controller1, ex1));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex2 = new CompoundStatement(new DeclarationStatement("a",new IntType()),
                new CompoundStatement(new DeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(1,new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression(3,new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression(1,new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        try{
            ex2.typeCheck(new MyDictionary<>());
            ProgramState prg2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex2, new MyHeap());
            IRepository repo2 = new Repository(prg2, "log2.txt");
            Controller controller2 = new Controller(repo2);
            menu.addCommand(new RunExampleCommand("2", " " +ex2, controller2, ex2));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex3 = new CompoundStatement(new DeclarationStatement("a", new BoolType()),
                new CompoundStatement(new DeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        try{
            ex3.typeCheck(new MyDictionary<>());
            ProgramState prg3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex3, new MyHeap());
            IRepository repo3 = new Repository(prg3, "log3.txt");
            Controller controller3 = new Controller(repo3);
            menu.addCommand(new RunExampleCommand("3", " " +ex3, controller3, ex3));
        }
        catch (Exception e){
                System.out.println(e.getMessage());
        }
        IStatement ex4 = new CompoundStatement(new DeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFile(new VariableExpression("varf")),
                                new CompoundStatement(new DeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFile(new VariableExpression("varf"))))))))));
        try{
            ex4.typeCheck(new MyDictionary<>());
            ProgramState prg4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex4, new MyHeap());
            IRepository repo4 = new Repository(prg4, "log4.txt");
            Controller controller4 = new Controller(repo4);
            menu.addCommand(new RunExampleCommand("4", " " +ex4, controller4, ex4));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex5 = new CompoundStatement(new DeclarationStatement("a", new IntType()),
                new CompoundStatement(new DeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(5))),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(7))),
                                        new IfStatement(new RelationalExpression( new VariableExpression("a"),
                                                new VariableExpression("b"), ">"),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));

        try{
            ex5.typeCheck(new MyDictionary<>());
            ProgramState prg5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex5, new MyHeap());
            IRepository repo5 = new Repository(prg5, "log5.txt");
            Controller controller5 = new Controller(repo5);
            menu.addCommand(new RunExampleCommand("5", " " +ex5, controller5, ex5));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex6 = new CompoundStatement(new DeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",new ArithmeticExpression(2, new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        try{
            ex6.typeCheck(new MyDictionary<>());
            ProgramState prg6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex6,new MyHeap());
            IRepository repo6 = new Repository(prg6, "log6.txt");
            Controller controller6 = new Controller(repo6);
            menu.addCommand(new RunExampleCommand("6", " " +ex6, controller6,ex6));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex7 = new CompoundStatement(new DeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new DeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        try{
            ex7.typeCheck(new MyDictionary<>());
            ProgramState prg7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex7,new MyHeap());
            IRepository repo7 = new Repository(prg7, "log7.txt");
            Controller controller7 = new Controller(repo7);
            menu.addCommand(new RunExampleCommand("7", " " +ex7, controller7, ex7));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex8 = new CompoundStatement(new DeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new DeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(1,new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
        try{
            ex8.typeCheck(new MyDictionary<>());
            ProgramState prg8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(),ex8,new MyHeap());
            IRepository repo8 = new Repository(prg8, "log8.txt");
            Controller controller8 = new Controller(repo8);
            menu.addCommand(new RunExampleCommand("8", " " +ex8, controller8, ex8));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex9 = new CompoundStatement(new DeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement( new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(1, new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
        try{
            ex9.typeCheck(new MyDictionary<>());
            ProgramState prg9 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex9,new MyHeap());
            IRepository repo9 = new Repository(prg9, "log9.txt");
            Controller controller9 = new Controller(repo9);
            menu.addCommand(new RunExampleCommand("9", " " +ex9, controller9, ex9));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        IStatement ex10 = new CompoundStatement(new DeclarationStatement("v", new IntType()),
                new CompoundStatement(new DeclarationStatement("a", new RefType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
        try{
            ex10.typeCheck(new MyDictionary<>());
            ProgramState prg10 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex10 ,new MyHeap());
            IRepository repo10 = new Repository(prg10, "log10.txt");
            Controller controller10 = new Controller(repo10);
            menu.addCommand(new RunExampleCommand("10", " " +ex10, controller10, ex10));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        menu.show();
    }
}