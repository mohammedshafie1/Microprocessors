package Main;

import Load_Instructions.*;
import Data.*;
import Reservation_Station.Add_RS;
import Reservation_Station.Load_RS;
import Reservation_Station.Mul_RS;
import Reservation_Station.Store_RS;

public class Checks {

    public static Pair Check_Add (String type){
        for (int i = 0; i < Main._ADD_RS.length ; i++) {
            if (!Main._ADD_RS[i].busy) {
                if (type.contains("ADD") || type.contains("SUB") || type.equals("BNEZ"))
                    return new Pair(true, i);
            }
        }
        return new Pair(false, null);
    }

    public static Pair Check_Mul (String type){
        for (int i = 0; i < Main._Mul_RS.length ; i++) {
            if (!Main._Mul_RS[i].busy) {
                if (type.contains("MUL") || type.contains("DIV"))
                    return new Pair(true, i);
            }
        }
        return new Pair(false, null);
    }

    public static Pair Check_Load (String type){
        for (int i = 0; i < Main._Load_RS.length ; i++) {
            if (!Main._Load_RS[i].busy) {
                if (type.contains("L.D") || type.contains("L.S") || type.contains("LD"))
                    return new Pair(true, i);
            }
        }
        return new Pair(false, null);
    }

    public static Pair Check_Store (String type){
        for (int i = 0; i < Main._Store_RS.length ; i++) {
            if (!Main._Store_RS[i].busy) {
                if (type.contains("S.D") || type.contains("S.S") || type.contains("SD"))
                    return new Pair(true, i);
            }
        }
        return new Pair(false, null);
    }

    public static boolean Check_Address_Load (String rs){
        for (int i = 0; i < Main._Store_RS.length ; i++){
            if (Main._Store_RS[i].address == Integer.parseInt(rs))
                return false;
        }
        return true;
    }

    public static boolean Check_Address_Store (String rs){
        for (int i = 0; i < Main._Load_RS.length ; i++){
            if (Main._Load_RS[i].address == Integer.parseInt(rs))
                return false;
        }

        for (int i = 0; i < Main._Store_RS.length ; i++){
            if (Main._Store_RS[i].address == Integer.parseInt(rs))
                return false;
        }
        return true;
    }

    public static boolean Check_Integer (String type){
        return !type.contains(".D") && !type.contains(".S");
    }

    public static boolean Check_Valid_RS (Instruction instruction){
        int index = Integer.parseInt(instruction.rs.substring(1));
        if (!Check_Integer(instruction.type)){
            if (RegisterFile.F[index].charAt(0) - '0' <= 9)
                return true;
            else
                return false;
        }

        else{
            if (RegisterFile.I[index].charAt(0) - '0' <= 9)
                return true;
            else
                return false;
        }
    }

    public static boolean Check_Valid_RT (Instruction instruction){
        int index = Integer.parseInt(instruction.rt.substring(1));
        if (!Check_Integer(instruction.type)){
            if (RegisterFile.F[index].charAt(0) - '0' <= 9)
                return true;
            else
                return false;
        }

        else{
            if (RegisterFile.I[index].charAt(0) - '0' <= 9)
                return true;
            else
                return false;
        }
    }

    public static boolean Check_Immediate (Instruction instruction){
        return instruction.rd.charAt(0) - '0' <= 9;
    }

    public static boolean Check_Valid_RD (Instruction instruction) {
        if (Check_Immediate(instruction))
            return true;
        int index = Integer.parseInt(instruction.rd.substring(1));
        if (!Check_Integer(instruction.type)){
            if (RegisterFile.F[index].charAt(0) - '0' <= 9)
                return true;
            else
                return false;
        }

        else{
            if (RegisterFile.I[index].charAt(0) - '0' <= 9)
                return true;
            else
                return false;
        }
    }

    public static String get_RS (Instruction instruction){
        int index = Integer.parseInt(instruction.rs.substring(1));
        if (Check_Integer(instruction.type))
            return RegisterFile.I[index];
        else
            return RegisterFile.F[index];
    }

    public static String get_RT (Instruction instruction){
        int index = Integer.parseInt(instruction.rt.substring(1));
        if (Check_Integer(instruction.type))
            return RegisterFile.I[index];
        else
            return RegisterFile.F[index];
    }

    public static String get_RD (Instruction instruction){
        if (Check_Immediate(instruction))
            return instruction.rd;
        int index = Integer.parseInt(instruction.rd.substring(1));
        if (Check_Integer(instruction.type))
            return RegisterFile.I[index];
        else
            return RegisterFile.F[index];
    }

    public static boolean checkFloat(Instruction instruction){
        return instruction.type.contains(".S") || instruction.type.contains(".D");
    }

    public static boolean Check_Add (Instruction instruction){
        return instruction.type.contains("ADD");
    }

    public static boolean Check_Sub (Instruction instruction){
        return instruction.type.contains("SUB");
    }

    public static boolean Check_Mul (Instruction instruction){
        return instruction.type.contains("MUL");
    }

    public static boolean Check_DIV (Instruction instruction){
        return instruction.type.contains("DIV");
    }

    public static boolean Check_Load (Instruction instruction){
        return instruction.type.equals("L.D") || instruction.type.equals("L.S") || instruction.type.equals("LD");
    }

    public static boolean Check_Store (Instruction instruction){
        return instruction.type.equals("S.D") || instruction.type.equals("S.S") || instruction.type.equals("SD");
    }

    public static boolean Check_Branch (Instruction instruction){
        return instruction.type.equals("BNEZ");
    }

    public static boolean Is_Available (String ID){
        if (ID.charAt(0) == 'A'){
            int index = ID.charAt(1) - '0';
            return  Main._ADD_RS[index].qj == null && Main._ADD_RS[index].qk == null;
        }

        if (ID.charAt(0) == 'M'){
            int index = ID.charAt(1) - '0';
            return  Main._Mul_RS[index].qj == null && Main._Mul_RS[index].qk == null;
        }

        if (ID.charAt(0) == 'S'){
            int index = ID.charAt(1) - '0';
            return  Main._Store_RS[index].qj == null;
        }

        if (ID.charAt(0) == 'L')
            return true;

        return false;
    }

    public static double getResult(String ID){

        double result = 0;

        if (ID.charAt(0) == 'A'){
            int index = ID.charAt(1) - '0';
            if(Main._ADD_RS[index].opcode.contains("ADD")){
                result = Double.parseDouble(Main._ADD_RS[index].vj) +  Double.parseDouble(Main._ADD_RS[index].vk);
            }else if (Main._ADD_RS[index].opcode.contains("SUB")){
                result = Double.parseDouble(Main._ADD_RS[index].vj) -  Double.parseDouble(Main._ADD_RS[index].vk);
            }
            else { // this is for branch
                result = Double.parseDouble(Main._ADD_RS[index].vj);
            }

        }

        if (ID.charAt(0) == 'M'){
            int index = ID.charAt(1) - '0';
            if(Main._Mul_RS[index].opcode.contains("MUL")){
                result = Double.parseDouble(Main._Mul_RS[index].vj) *  Double.parseDouble(Main._Mul_RS[index].vk);
            }else{
                result = Double.parseDouble(Main._Mul_RS[index].vj) /  Double.parseDouble(Main._Mul_RS[index].vk);
            }
        }

        if (ID.charAt(0) == 'S'){
            int index = ID.charAt(1) - '0';
            result = Double.parseDouble(Main._Store_RS[index].vj);
        }

        if (ID.charAt(0) == 'L'){
            int index = ID.charAt(1) - '0';
            result = Cache.cache[Main._Load_RS[index].address];
        }

        return result;
    }

    public static void Handle_Branch(Instruction inst){
        Main.pc = 0;
        Main.iteration++;
        for (Instruction instruction : Main.instructions){
            if (instruction.loop != null && instruction.loop.equals(inst.rt))
                break;
            else
                Main.pc++;
        }
    }

    public static boolean Is_Loop(String type){
        if (type.contains("MUL") || type.contains("ADD") || type.contains("SUB")
        || type.contains("DIV") || type.contains("L.S") || type.contains("L.D")
        || type.contains("S.S") || type.contains("S.D") || type.contains("BNEZ")
        || type.contains("LD") || type.contains("SD"))
            return false;
        return true;
    }

    public static void Initialize (){
        for (int i = 0 ; i < Main._ADD_RS.length ; i++){
            Add_RS add_rs = new Add_RS(
                    "A" + i,
                    null,null,null,null,null,false
            );
            Main._ADD_RS[i] = add_rs;
        }

        for (int i = 0 ; i < Main._Mul_RS.length ; i++){
            Mul_RS mul_rs = new Mul_RS(
                    "M" + i,
                    null,null,null,null,null,false
            );
            Main._Mul_RS[i] = mul_rs;
        }

        for (int i = 0 ; i < Main._Load_RS.length ; i++){
            Load_RS load_rs = new Load_RS(
                    "L" + i,
                    null,-1,false
            );
            Main._Load_RS[i] = load_rs;
        }

        for (int i = 0 ; i < Main._Store_RS.length ; i++){
            Store_RS store_rs = new Store_RS(
                    "S" + i,
                    null,null,null,-1,false
            );
            Main._Store_RS[i] = store_rs;
        }

        for (int i = 0; i < RegisterFile.I.length ; i++){
            RegisterFile.I[i] = "0";
        }

        for (int i = 0; i < RegisterFile.F.length ; i++){
            RegisterFile.F[i] = "0";
        }

    }

}
