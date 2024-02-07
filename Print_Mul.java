package Print;

import Main.*;
import Reservation_Station.*;

import java.io.PrintWriter;

public class Print_Mul {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void Print_Mul (){
        pw.println("Mul Reservation Station");
        pw.println("-------------------------------------------");
        for (Mul_RS mul_rs: Main._Mul_RS){
            pw.print(mul_rs.id+"  ");
            pw.print(mul_rs.opcode != null ? mul_rs.opcode + "  " : "  ");
            pw.print(mul_rs.vj != null ? mul_rs.vj + "  " : "  ");
            pw.print(mul_rs.vk != null ? mul_rs.vk + "  " : "  ");
            pw.print(mul_rs.qj != null ? mul_rs.qj + "  " : "  ");
            pw.print(mul_rs.qk != null ? mul_rs.qk + "  " : "  ");
            pw.print(mul_rs.busy ? 1 : 0);
            pw.println();
        }
        pw.println();
        pw.flush();
    }
}
