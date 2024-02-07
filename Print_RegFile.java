package Print;

import Data.RegisterFile;

import java.io.PrintWriter;

public class Print_RegFile {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void Print_RegFile_F (){
        pw.println("Register File of Float");
        pw.println("-------------------------------------------");
        int c = 0;
        for (String s : RegisterFile.F){
            pw.println("F"+(c++) +"   "+ s);
        }
        pw.println();
        pw.flush();
    }

    public static void Print_RegFile_I (){
        pw.println("Register File of Integer");
        pw.println("-------------------------------------------");
        int c = 0;
        for (String s : RegisterFile.I){
            pw.println("R"+(c++) +"   "+ s);
        }
        pw.println();
        pw.flush();
    }

}
