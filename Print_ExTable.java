package Print;

import Load_Instructions.Instruction;
import Main.Execution;
import Main.ExecutionTable;

import java.io.PrintWriter;

public class Print_ExTable {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void Print_ExTable (){
        pw.println("Excution Table");
        pw.println("-------------------------------------------");
        for (ExecutionTable executionTable: Execution.executionTable){
            Instruction instruction = executionTable.instruction;
            pw.print(instruction.type+"  ");
            pw.print(instruction.rs+"  ");
            pw.print(instruction.rt+"  ");
            pw.print(instruction.rd != null ? instruction.rd +"  " : "  ");
            pw.print(executionTable.issue+"   ");
            for (int ex: executionTable.execute)
                pw.print(ex+" ");
            pw.print("   ");
            pw.print(executionTable.writeResult != null ? executionTable.writeResult +"  " : "  ");
            pw.println();
        }
        pw.println();
        pw.flush();
    }

}
