module toylanguage.toy_language_interpreter_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens toylanguage.toy_language_interpreter_gui to javafx.fxml;
    exports toylanguage.toy_language_interpreter_gui;
}