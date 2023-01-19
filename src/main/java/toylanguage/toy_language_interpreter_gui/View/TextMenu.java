package toylanguage.toy_language_interpreter_gui.View;

import toylanguage.toy_language_interpreter_gui.Model.Adts.IDictionary;
import toylanguage.toy_language_interpreter_gui.Model.Adts.MyDictionary;
import toylanguage.toy_language_interpreter_gui.MyException.ADTException;

import java.util.Scanner;

public class TextMenu {
    private IDictionary<String, Command> commands;
    public TextMenu(){
        commands = new MyDictionary<>();
    }
    public void addCommand(Command command){
        commands.put(command.getKey(), command);
    }
    private void printMenu(){
        for (Command command:commands.getContent().values()){
            String line = command.getKey() + command.getDescription();
            System.out.println(line);
        }
    }
    public void show(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            printMenu();
            System.out.println("Input the option: ");
            String key = scanner.nextLine();
            try{
                Command command = commands.get(key);
                command.execute();
            }
            catch (ADTException exception){
                System.out.println("Invalid command!");
            }
        }
    }
}
