package Print;

import Main.Main;
import Reservation_Station.Load_RS;

import java.io.PrintWriter;

public class Print_Load {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void Print_Load (){
        pw.println("Load Reservation Station");
        pw.println("-------------------------------------------");
        for (Load_RS load_rs: Main._Load_RS){
            pw.print(load_rs.id+"  ");
            pw.print(load_rs.opcode != null ? load_rs.opcode + "  " : "  ");
            pw.print(load_rs.address != -1 ? load_rs.address + "  " : "  ");
            pw.print(load_rs.busy ? 1 : 0);
            pw.println();
        }
        pw.println();
        pw.flush();
    }
}
