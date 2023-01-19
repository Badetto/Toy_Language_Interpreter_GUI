package toylanguage.toy_language_interpreter_gui.View;

import toylanguage.toy_language_interpreter_gui.Controller.Controller;
import toylanguage.toy_language_interpreter_gui.Model.Adts.MyDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.MyHeap;
import toylanguage.toy_language_interpreter_gui.Model.Adts.MyList;
import toylanguage.toy_language_interpreter_gui.Model.Adts.MyStack;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.Model.Statements.IStatement;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import toylanguage.toy_language_interpreter_gui.Repository.IRepository;
import toylanguage.toy_language_interpreter_gui.Repository.Repository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RunExampleCommand extends Command{
    private final Controller controller;
    private IStatement istm;
    public RunExampleCommand(String key, String desc,Controller new_ctr, IStatement istm){
        super(key, desc);
        controller=new_ctr;
        this.istm = istm;
    }
    @Override
    public void execute() {
        try{
            ProgramState prg = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), istm ,new MyHeap());
            IRepository repo = new Repository(prg, "log.txt");
            Controller controller = new Controller(repo);

            System.out.println("Do you want to display the steps?[Y/N]");
            Scanner readOption = new Scanner(System.in);
            String option = readOption.next();
            if (Objects.equals(option, "Y"))
                controller.setDisplayFlag(true);
            controller.allSteps();
            System.out.println(prg.toString() + "\n");
        }
        catch (Exception  e){
            System.out.println(e.getMessage());
        }
    }
}
