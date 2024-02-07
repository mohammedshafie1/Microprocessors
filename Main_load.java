package Load_Instructions;

import Main.Checks;

import java.io.*;
import java.util.*;

public class Main_load {

    public static ArrayList<Instruction> readInstructionsFromFile(String filePath) throws IOException {
        ArrayList<Instruction> instructions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int i = 0;
                String loop = null;
                if (Checks.Is_Loop(parts[0])){
                    loop = parts[0];
                    i++;
                }
                String type = parts[0 + i];
                String rs = parts[1 + i];
                String rt = parts[2 + i];
                String rd = null;
                if (parts[0 + i].contains("ADD") || parts[0 + i].contains("SUB") || parts[0 + i].contains("MUL") || parts[0 + i].contains("DIV")) {
                    rd = parts[3 + i];
                }
                Instruction instruction = null;
                if (loop == null)
                    instruction = new Instruction(type,rs,rt,rd);
                else
                    instruction = new Instruction(loop, type,rs,rt,rd);
                instructions.add(instruction);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
        return instructions;
    }
}
