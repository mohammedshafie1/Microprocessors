package Print;

import Main.Main;
import Reservation_Station.Store_RS;

import java.io.PrintWriter;

public class Print_Store {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void Print_Store (){
        pw.println("Store Reservation Station");
        pw.println("-------------------------------------------");
        for (Store_RS store_rs: Main._Store_RS){
            pw.print(store_rs.id+"  ");
            pw.print(store_rs.opcode != null ? store_rs.opcode + "  " : "  ");
            pw.print(store_rs.vj != null ? store_rs.vj + "  " : "  ");
            pw.print(store_rs.qj != null ? store_rs.qj + "  " : "  ");
            pw.print(store_rs.address != -1 ? store_rs.address + "  " : "  ");
            pw.print(store_rs.busy ? 1 : 0);
            pw.println();
        }
        pw.println();
        pw.flush();
    }
}
