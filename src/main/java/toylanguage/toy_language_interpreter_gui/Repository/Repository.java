package toylanguage.toy_language_interpreter_gui.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import toylanguage.toy_language_interpreter_gui.Model.ProgramState;
import toylanguage.toy_language_interpreter_gui.MyException.*;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private final String logFilePath;
    public Repository(ProgramState new_programState, String new_logFile_path) throws IOException{
        logFilePath = new_logFile_path;
        programStates = new ArrayList<>();
        this.addProgram(new_programState);

    }
    public List<ProgramState> getProgramStates(){
        return programStates;
    }
    public void setProgramStates(List<ProgramState> new_programStates){
        this.programStates = new_programStates;
    }
    public void addProgram(ProgramState new_programState) {
        this.programStates.add(new_programState);
    }
    @Override
    public void logPrgStateExec(ProgramState programState) throws StatementException, ExpressionException, ADTException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(programState.programStateToString());
        logFile.close();
    }

    @Override
    public void emptyLogFile() throws IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.close();
    }
}
