package Main;

import Data.*;
import Load_Instructions.*;
import java.util.*;


public class Execution {

    public static ArrayList<ExecutionTable> executionTable = new ArrayList<>();
    public static Queue<Pair> publishedData = new LinkedList<>();


    public static void addToTable(String id, Instruction instruction){
        Integer issue = Main.clockCycle;
        Queue<Integer> execute = new LinkedList<>();
        ExecutionTable  entry = new ExecutionTable(Main.iteration,id,instruction,issue,execute, null);
        executionTable.add(entry);
    }

    public static void executeTable(){

        for(ExecutionTable executionTableEntry: executionTable){
            Instruction instruction = executionTableEntry.instruction;
            Integer issue = executionTableEntry.issue;
            boolean finish = executionTableEntry.finish;

            if (issue == Main.clockCycle){ //it is issued in this clock cycle
                if (!(instruction.type.equals("BNEZ") ||
                        instruction.type.equals("S.D") ||
                        instruction.type.equals("S.S") ||
                        instruction.type.equals("SD")))
                    //if it is issued in this cycle I have to write the result in the register file
                    // (these instructions do not write in the register file upon finishing)
                {
                    int index =  Integer.parseInt(executionTableEntry.instruction.rs.substring(1));
                    if (Checks.checkFloat(instruction)) {
                        RegisterFile.F[index] = executionTableEntry.id;
                    } else {
                        RegisterFile.I[index] = executionTableEntry.id;
                    }
                }
            }

            else if(!finish) { //try to execute any unfinished instructions
                if (Checks.checkFloat(instruction)) {
                    executeFloat(executionTableEntry);
                } else {
                    executeInteger(executionTableEntry);
                }
            }
        }

        if (!publishedData.isEmpty()){ //publish the data if any priority is FIFO because we use queue
            Pair p = publishedData.remove();
            Publish_Result(p);
        }

    }

    public static void executeInteger(ExecutionTable executionTable){

        if(Checks.Check_Store(executionTable.instruction)){ //check if it is a store instruction
            if(executionTable.execute.size() == Main.storeILatency){ //check if it fished execution
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable, Checks.getResult(executionTable.id))); //compute the result and add to the publishedData queue
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){ //if it is still has not begun execution check first if it can even execute
                    if(Checks.Is_Available(executionTable.id)){ //so we will check if the operands it needs are ready
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_Load(executionTable.instruction)){
            if(executionTable.execute.size() == Main.loadILatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable, Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_Add(executionTable.instruction)){
            if(executionTable.execute.size() == Main.addILatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable,(int)Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_Sub(executionTable.instruction)){
            if(executionTable.execute.size() == Main.subILatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable, Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_Branch(executionTable.instruction)){
            if(executionTable.execute.size() == Main.branchLatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable, Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }
    }

    public static void executeFloat(ExecutionTable executionTable){

        if(Checks.Check_Store(executionTable.instruction)){
            if(executionTable.execute.size() == Main.storeLatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable, Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_Load(executionTable.instruction)){
            if(executionTable.execute.size() == Main.loadLatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable, Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_Add(executionTable.instruction)){
            if(executionTable.execute.size() == Main.addLatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable, Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_Sub(executionTable.instruction)){
            if(executionTable.execute.size() == Main.subLatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable, Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_Mul(executionTable.instruction)){
            if(executionTable.execute.size() == Main.mulLatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable,Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }

        if(Checks.Check_DIV(executionTable.instruction)){
            if(executionTable.execute.size() == Main.divLatency){
                executionTable.finish = true;
                publishedData.add(new Pair(executionTable,Checks.getResult(executionTable.id)));
            }
            else{
                //still in execution
                if(executionTable.execute.size() == 0){
                    if(Checks.Is_Available(executionTable.id)){
                        executionTable.execute.add(Main.clockCycle);
                    }
                }
                else {
                    executionTable.execute.add(Main.clockCycle);
                }
            }
        }
    }

    //this method clears the reservation station
    public static void Update_RS(String id){

        for (int i = 0; i < Main._ADD_RS.length ; i++){
            if (Main._ADD_RS[i].id.equals(id)){
                Main._ADD_RS[i].opcode = null;
                Main._ADD_RS[i].vj = null;
                Main._ADD_RS[i].vk = null;
                Main._ADD_RS[i].qj = null;
                Main._ADD_RS[i].qk = null;
                Main._ADD_RS[i].busy = false;
            }
        }

        for (int i = 0; i < Main._Mul_RS.length ; i++){
            if (Main._Mul_RS[i].id.equals(id)){
                Main._Mul_RS[i].opcode = null;
                Main._Mul_RS[i].vj = null;
                Main._Mul_RS[i].vk = null;
                Main._Mul_RS[i].qj = null;
                Main._Mul_RS[i].qk = null;
                Main._Mul_RS[i].busy = false;
            }
        }

        for (int i = 0; i < Main._Store_RS.length ; i++){
            if (Main._Store_RS[i].id.equals(id)){
                Main._Store_RS[i].opcode = null;
                Main._Store_RS[i].vj = null;
                Main._Store_RS[i].qj = null;
                Main._Store_RS[i].address = -1;
                Main._Store_RS[i].busy = false;
            }
        }

        for (int i = 0; i < Main._Load_RS.length ; i++){
            if (Main._Load_RS[i].id.equals(id)){
                Main._Load_RS[i].opcode = null;
                Main._Load_RS[i].address = -1;
                Main._Load_RS[i].busy = false;
            }
        }
    }

    public static void Publish_Result(Pair p){

        ExecutionTable executionTable = (ExecutionTable) p.first;
        String id = executionTable.id;
        String value = "" + p.second;

        //convert it to int removes any .0
        if (!executionTable.instruction.type.contains(".S") && !executionTable.instruction.type.contains(".D"))
            value = String.valueOf((int) Double.parseDouble(value));

        //Floating register file will grab it if it needs
        for(int i = 0; i < RegisterFile.F.length ; i++){
            if (RegisterFile.F[i].equals(id))
                RegisterFile.F[i] = value;
        }

        //Integer register file will grab it if it needs
        for(int i = 0; i < RegisterFile.I.length ; i++){
            if (RegisterFile.I[i].equals(id)) {
                RegisterFile.I[i] = value;
            }
        }

        //ADD RS grab
        for (int i = 0; i < Main._ADD_RS.length ; i++){
            if (Main._ADD_RS[i].qj != null && Main._ADD_RS[i].qj.equals(id)){
                Main._ADD_RS[i].qj = null;
                Main._ADD_RS[i].vj = value;
            }
            if (Main._ADD_RS[i].qk != null && Main._ADD_RS[i].qk.equals(id)){
                Main._ADD_RS[i].qk = null;
                Main._ADD_RS[i].vk = value;
            }
        }

        //MUL RS grab
        for (int i = 0; i < Main._Mul_RS.length ; i++){
            if (Main._Mul_RS[i].qj != null && Main._Mul_RS[i].qj.equals(id)){
                Main._Mul_RS[i].qj = null;
                Main._Mul_RS[i].vj = value;
            }
            if (Main._Mul_RS[i].qk != null && Main._Mul_RS[i].qk.equals(id)){
                Main._Mul_RS[i].qk = null;
                Main._Mul_RS[i].vk = value;
            }
        }

        //STORE RS grab
        for (int i = 0; i < Main._Store_RS.length ; i++){
            if (Main._Store_RS[i].qj != null && Main._Store_RS[i].qj.equals(id)){
                Main._Store_RS[i].qj = null;
                Main._Store_RS[i].vj = value;
            }
        }

        if (Checks.Check_Integer(executionTable.instruction.type)){
            if (Checks.Check_Store(executionTable.instruction)){ //check store integr
                Cache.cache [Integer.parseInt(executionTable.instruction.rt)] = (int) Double.parseDouble(value);
            }
        }
        else{
            if (Checks.Check_Store(executionTable.instruction)){ //check store float
                Cache.cache [Integer.parseInt(executionTable.instruction.rt)] = Double.parseDouble(value);
            }
        }

        if (Checks.Check_Branch(executionTable.instruction)){
            if (Integer.parseInt(value) != 0) //branch will be taken
                Checks.Handle_Branch(executionTable.instruction);
            Main.branch = false; //set it to false to continue issuing normally
        }

        executionTable.writeResult = Main.clockCycle;
        Update_RS(executionTable.id);
    }
}
