package Print;

import Data.Cache;
import Data.RegisterFile;
import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;

public class Print {
    static GUI gui;
    static PrintWriter pw = new PrintWriter(System.out);

    public static void Print (){
//        pw.println("Clock Cycle number " + Main.clockCycle);
//        pw.println();
//        pw.flush();
//        Print_Add.Print_Add();
//        Print_Mul.Print_Mul();
//        Print_Load.Print_Load();
//        Print_Store.Print_Store();
//        Print_RegFile.Print_RegFile_F();
//        Print_RegFile.Print_RegFile_I();
//        Print_ExTable.Print_ExTable();
    }

    public static void printGui(){
        //creation of the tables and text
        String clockCycle ="CLOCK CYCLE: "+Main.clockCycle;
        JTable executionTable = GUI.createExecutionTable(Execution.executionTable);
        JTable AddRsTable = GUI.createAddRsTable(Main._ADD_RS);
        JTable MulRsTable = GUI.createMulRsTable(Main._Mul_RS);
        JTable LoadRsTable = GUI.createLoadRsTable(Main._Load_RS);
        JTable StoreRsTable = GUI.createStoreRsTable(Main._Store_RS);
        JTable RegITable = GUI.createRegFileIntegerTable(RegisterFile.I);
        JTable RegFTable = GUI.createRegFileFloatTable(RegisterFile.F);
        JTable CacheTable = GUI.createCacheTable(Cache.cache);


        //add the tables and text created
        gui.addText(clockCycle,"");
        gui.addTable(executionTable,"EXECUTION TABLE: ");
        gui.addTable(AddRsTable,"ADD RESERVATION STATION: ");
        gui.addTable(MulRsTable, "MULTIPLY RESERVATION STATION");
        gui.addTable(LoadRsTable , "LOAD RESERVATION STATION: ");
        gui.addTable(StoreRsTable,"STORE RESERVATION STATION: ");
        gui.addTable(RegITable,"INTEGER REGISTER FILE: ");
        gui.addTable(RegFTable,"FLOATING POINT REGISTER FILE: ");
        gui.addTable(CacheTable,"CACHE: ");
    }

    public Print() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("TOMASULO GUI SIMULATION ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            gui = new GUI(mainPanel); //this main panel is where all the tables/text will be displayed the methods inside this class will use this main panel

            int default_mulLatency = 5;
            int default_addLatency = 3;
            int default_subLatency = 3;
            int default_divLatency = 7;
            int default_loadLatency = 3;
            int default_storeLatency = 1;

            int default_subILatency = 1;
            int default_addILatency = 1;
            int default_loadILatency = 1;
            int default_storeILatency = 1;
            int default_branchLatency = 1;


            // Add input components
            Label addLatency = new Label("ADD LATENCY:");
            TextField addLatencyTextField = new TextField(default_addLatency+"");

            Label subLatency = new Label("SUB LATENCY:");
            TextField subLatencyTextField = new TextField(default_subLatency+"");

            Label mulLatency = new Label("MUL LATENCY:");
            TextField mulLatencyTextField = new TextField(default_mulLatency+"");

            Label divLatency = new Label("DIV LATENCY:");
            TextField divLatencyTextField = new TextField(default_divLatency+"");

            Label loadLatency = new Label("LOAD LATENCY:");
            TextField loadLatencyTextField = new TextField(default_loadLatency+"");

            Label storeLatency = new Label("STORE LATENCY:");
            TextField storeLatencyTextField = new TextField(default_storeLatency+"");

            Label addILatency = new Label("ADDI LATENCY:");
            TextField addILatencyTextField = new TextField(default_addILatency+"");

            Label subILatency = new Label("SUBI LATENCY:");
            TextField subILatencyTextField = new TextField(default_subILatency+"");

            Label loadILatency = new Label("LOADI LATENCY:");
            TextField loadILatencyTextField = new TextField(default_loadILatency+"");

            Label storeILatency = new Label("STOREI LATENCY:");
            TextField storeILatencyTextField = new TextField(default_storeILatency+"");

            Label branchLatency = new Label("BRANCH LATENCY:");
            TextField branchLatencyTextField = new TextField(default_branchLatency+"");


            Button runButton = new Button("Run");

            // Set up button action
            runButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get user input
                    Main.addLatency = Integer.parseInt(addLatencyTextField.getText());

                    Main.subLatency = Integer.parseInt(subLatencyTextField.getText());

                    Main.mulLatency = Integer.parseInt(mulLatencyTextField.getText());

                    Main.divLatency = Integer.parseInt(divLatencyTextField.getText());

                    Main.loadLatency = Integer.parseInt(loadLatencyTextField.getText());

                    Main.storeLatency = Integer.parseInt(storeLatencyTextField.getText());

                    Main.addILatency = Integer.parseInt(addILatencyTextField.getText());

                    Main.subILatency = Integer.parseInt(subILatencyTextField.getText());

                    Main.loadILatency = Integer.parseInt(loadILatencyTextField.getText());

                    Main.storeILatency = Integer.parseInt(storeILatencyTextField.getText());

                    Main.branchLatency = Integer.parseInt(branchLatencyTextField.getText());

                    // Run your methods here
                    try {
                        Main.main(); //run the main method that will simulate tomasulo
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            mainPanel.add(addLatency);
            mainPanel.add(addLatencyTextField);

            mainPanel.add(subLatency);
            mainPanel.add(subLatencyTextField);

            mainPanel.add(mulLatency);
            mainPanel.add(mulLatencyTextField);

            mainPanel.add(divLatency);
            mainPanel.add(divLatencyTextField);

            mainPanel.add(loadLatency);
            mainPanel.add(loadLatencyTextField);

            mainPanel.add(storeLatency);
            mainPanel.add(storeLatencyTextField);

            mainPanel.add(addILatency);
            mainPanel.add(addILatencyTextField);

            mainPanel.add(subILatency);
            mainPanel.add(subILatencyTextField);

            mainPanel.add(loadILatency);
            mainPanel.add(loadILatencyTextField);

            mainPanel.add(storeILatency);
            mainPanel.add(storeILatencyTextField);

            mainPanel.add(branchLatency);
            mainPanel.add(branchLatencyTextField);

            mainPanel.add(runButton);

            JScrollPane scrollPane = new JScrollPane(mainPanel);

            frame.add(scrollPane);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
