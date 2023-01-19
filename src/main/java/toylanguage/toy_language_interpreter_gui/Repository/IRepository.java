package toylanguage.toy_language_interpreter_gui.Repository;

import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.MyException.*;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    List<ProgramState> getProgramStates();
    void setProgramStates(List<ProgramState> new_programStates);
    void addProgram(ProgramState new_programState);
    void logPrgStateExec(ProgramState programState)throws StatementException, ExpressionException, ADTException, IOException;
    void emptyLogFile() throws IOException;
}
