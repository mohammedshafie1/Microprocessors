package Main;

import Load_Instructions.Instruction;
import java.util.Queue;

public class ExecutionTable {

    public int iteration;
    public String id;
    public Instruction instruction;
    public Integer issue;
    public Queue<Integer> execute;
    public Integer writeResult;
    public boolean finish = false;

    public ExecutionTable(Integer iteration , String id, Instruction instruction, Integer issue, Queue<Integer> execute, Integer writeResult){
        this.iteration=iteration;
        this.id = id;
        this.instruction = instruction;
        this.issue = issue;
        this.execute = execute;
        this.writeResult = writeResult;
    }

}
