package toylanguage.toy_language_interpreter_gui.View;

public abstract class Command {
    private final String key, description;
    public Command(String new_key, String new_description) {
        key = new_key;
        description = new_description;
    }
    public abstract void execute();
    public String getKey(){
        return key;
    }
    public String getDescription(){
        return description;
    }
}
