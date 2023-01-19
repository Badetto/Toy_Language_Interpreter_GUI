package toylanguage.toy_language_interpreter_gui.View;

public class ExitCommand extends Command{
    public ExitCommand(String key, String desc){
        super(key, desc);
    }
    @Override
    public void execute() {
        System.exit(0);
    }
}
