package Print;

import Main.*;
import Reservation_Station.*;

import java.io.*;

public class Print_Add {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void Print_Add (){
        pw.println("Add Reservation Station");
        pw.println("-------------------------------------------");
        for (Add_RS add_rs : Main._ADD_RS){
            pw.print(add_rs.id+"  ");
            pw.print(add_rs.opcode != null ? add_rs.opcode + "  " : "  ");
            pw.print(add_rs.vj != null ? add_rs.vj + "  " : "  ");
            pw.print(add_rs.vk != null ? add_rs.vk + "  " : "  ");
            pw.print(add_rs.qj != null ? add_rs.qj + "  " : "  ");
            pw.print(add_rs.qk != null ? add_rs.qk + "  " : "  ");
            pw.print(add_rs.busy ? 1 : 0);
            pw.println();
        }
        pw.println();
        pw.flush();
    }
}
