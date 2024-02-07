package Main;

import Data.*;
import Print.Print;
import Reservation_Station.*;
import Load_Instructions.*;
import java.util.*;
import java.io.*;

public class Main {

    static int size = 3;
    public static Add_RS[] _ADD_RS = new Add_RS[size];
    public static Mul_RS[] _Mul_RS = new Mul_RS[size];
    public static Store_RS[] _Store_RS = new Store_RS[size];
    public static Load_RS[] _Load_RS = new Load_RS[size];
    public static ArrayList <Instruction> instructions;
    public static int clockCycle = 1;
    public static int mulLatency = 5;
    public static int addLatency = 3;
    public static int subLatency = 3;
    public static int divLatency = 7;
    public static int loadLatency = 3;
    public static int storeLatency = 1;

    public static int subILatency = 1;
    public static int addILatency = 1;
    public static int loadILatency = 1;
    public static int storeILatency = 1;
    public static int branchLatency = 1;

    public static boolean branch = false;
    static int pc = 0;
    public static int iteration = 0;


    public static String Add_To_RS (Instruction instruction){
        String type = instruction.type;
        Pair pair = Checks.Check_Add(type);

        if ((boolean)pair.first){
            Add_RS add_rs = null;
            if (type.equals("BNEZ")){
                add_rs = new Add_RS(
                        "A"+pair.second,
                        /*opcode*/instruction.type,
                        /*vj*/Checks.Check_Valid_RS(instruction) ? Checks.get_RS(instruction) : null,
                        /*vk*/null, //there is no vk in branch
                        /*qj*/!Checks.Check_Valid_RS(instruction) ? Checks.get_RS(instruction) : null,
                        /*qk*/null, //there is no qk in branch
                        true
                );
                branch = true;
            }
            else {
                add_rs = new Add_RS(
                        "A"+pair.second,
                        /*opcode*/instruction.type,
                        /*vj*/Checks.Check_Valid_RT(instruction) ? Checks.get_RT(instruction) : null,
                        /*vk*/Checks.Check_Valid_RD(instruction) ? Checks.get_RD(instruction) : null,
                        /*qj*/!Checks.Check_Valid_RT(instruction) ? Checks.get_RT(instruction) : null,
                        /*qk*/!Checks.Check_Valid_RD(instruction) ? Checks.get_RD(instruction) : null,
                        true
                );
            }
            _ADD_RS[(Integer) pair.second] = add_rs;
            return "A" + pair.second;
        }

        pair = Checks.Check_Mul(type);
        if ((boolean)pair.first){
            Mul_RS mul_rs = new Mul_RS(
                    "M"+pair.second,
                   /*opcode*/ instruction.type,
                    /*vj*/Checks.Check_Valid_RT(instruction) ? Checks.get_RT(instruction) : null,
                    /*vk*/Checks.Check_Valid_RD(instruction) ? Checks.get_RD(instruction) : null,
                    /*qj*/!Checks.Check_Valid_RT(instruction) ? Checks.get_RT(instruction) : null,
                    /*qk*/!Checks.Check_Valid_RD(instruction) ? Checks.get_RD(instruction) : null,
                    true
            );
            _Mul_RS[(Integer)pair.second] = mul_rs;
            return "M" + pair.second;
        }

        pair = Checks.Check_Load(type);
        if ((boolean)pair.first && Checks.Check_Address_Load(instruction.rt)){
            //second check is to make sure there is no store that is executing or issued with the same address
            Load_RS load_rs = new Load_RS(
                    "L"+pair.second,
                    /*opcode*/instruction.type,
                    /*address*/Integer.parseInt(instruction.rt),
                    true
            );
            _Load_RS[(Integer)pair.second] = load_rs;
            return "L" + pair.second;
        }

        pair = Checks.Check_Store(type);
        if ((boolean)pair.first && Checks.Check_Address_Store(instruction.rt)){
            //second check is to make sure there is no load or store that is executing with the same address
            Store_RS store_rs = new Store_RS(
                    "S"+pair.second,
                    /*opcode*/instruction.type,
                    /*vj*/Checks.Check_Valid_RS(instruction) ? Checks.get_RS(instruction) : null,
                    /*qj*/!Checks.Check_Valid_RS(instruction) ? Checks.get_RS(instruction) : null,
                    /*address*/Integer.parseInt(instruction.rt),
                    true
            );
            _Store_RS[(Integer)pair.second] = store_rs;
            return "S" + pair.second;
        }
        return null;
    }

    //--------------------------------------------------------------------------

    public static Print gui;

    public static void main (String [] args) throws IOException {
         gui = new Print(); //initialize the JFrame and JPanel
    }

    public static void main () throws IOException {

        Checks.Initialize();
        instructions = Main_load.readInstructionsFromFile("src/1.text");

        Cache.cache[1] = 4 ;
        Cache.cache[2] = 8 ;
        Cache.cache[6] = 24 ;

        while(true){
            if (!branch) { //stall until branch computes its result
                if (pc >= instructions.size()){ //this means all instructions have been issued ,so we just execute the execution table
                    boolean f = true;
                    for (ExecutionTable executionTable: Execution.executionTable){
                        if (executionTable.writeResult == null)
                            f = false;
                    }
                    if (f)
                        break;
                }
                else{
                    Instruction instruction  = instructions.get(pc); //get the instruction
                    String id = Add_To_RS(instruction); //add it to a reservation station if there is a place
                    if (id != null) {
                        Execution.addToTable(id,instruction); //if place found put the instruction in the execution table
                        pc++;
                    }
                }
            }

            Execution.executeTable(); //execute
            Print.Print(); //printing
            Print.printGui();; //GUI
            clockCycle++;
        }
    }
}