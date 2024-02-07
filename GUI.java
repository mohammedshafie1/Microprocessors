package Print;

import Main.ExecutionTable;
import Reservation_Station.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI {

    private JPanel contentPanel;

    public GUI(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public void addContent(Object content, String title) {
        if (content instanceof JTable) {
            addTable((JTable) content, title);
        } else if (content instanceof String) {
            addText((String) content, title);
        }
    }

    public void addTable(JTable table, String title) {

        // Table header enhancements
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(70, 130, 180)); // Steel Blue
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Customize the appearance based on the selection state
                if (isSelected) {
                    setBackground(new Color(100, 149, 237)); // Cornflower blue
                    setForeground(Color.BLACK); // Set text color when selected
                } else {
                    // Background color for alternating rows
                    setBackground(row % 2 == 0 ? Color.WHITE : new Color(200, 200, 200));
                    setForeground(Color.BLACK); // Set default text color
                }

//                setHorizontalAlignment(JLabel.CENTER); // Center-align cell contents

                return this;
            }
        });

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(title));
        tablePanel.add(new JScrollPane(table));

        contentPanel.add(tablePanel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void addText(String text, String title) {
        JLabel label = new JLabel(text);

        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createEmptyBorder(50, 0, 10, 0));

        contentPanel.add(label);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Table Adder Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            GUI tableAdder = new GUI(mainPanel);

            // Example of adding a table
            String[] dataArray = {"Data 1", "Data 2", "Data 3"};
            String title = "Example Table";
            JTable exampleTable = createTable(dataArray);
            tableAdder.addContent(exampleTable, title);

            // Example of adding text
            String textContent = "This is some text content.";
            String textTitle = "Text Section";
            tableAdder.addContent(textContent, textTitle);

            // Add input components
            Label inputLabel = new Label("Enter Input:");
            TextField inputTextField = new TextField();
            Button runButton = new Button("Run");

            // Set up button action
            runButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get user input
                    String userInput = inputTextField.getText();

                    // Run your methods here
                    // For this example, let's just display a simple table
                    String[] data = {"User Input", userInput};
                    String title = "User Input Table";
                    JTable userInputTable = createTable(data);
                    tableAdder.addContent(userInputTable, title);
                }
            });

            mainPanel.add(inputLabel);
            mainPanel.add(inputTextField);
            mainPanel.add(runButton);

            // Wrap the mainPanel in a JScrollPane for scrollability
            JScrollPane scrollPane = new JScrollPane(mainPanel);

            frame.add(scrollPane);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static JTable createTable(String[] dataArray) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Column 1");

        // Populate the table with data from the array
        for (String data : dataArray) {
            model.addRow(new Object[]{data});
        }

        return new JTable(model);
    }

    public static JTable createExecutionTable(ArrayList<ExecutionTable> executionTables) {
        //String iteration
//         String id;
//         Instruction instruction;
//         Integer issue;
//         Queue<Integer> execute;
//         Integer writeResult;
//         boolean finish = false;

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ITERATION: ");
        model.addColumn("ID");
        model.addColumn("Instruction");
        model.addColumn("Issue");
        model.addColumn("Execute");
        model.addColumn("Write Result");

        // Populate the table with data from the ArrayList
        for (ExecutionTable executionTable : executionTables) {
            model.addRow(new Object[]{
                    executionTable.iteration,
                    executionTable.id,
                    executionTable.instruction.toString(),
                    executionTable.issue,
                    executionTable.execute.toString(),
                    executionTable.writeResult,
            });
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize()); // Set preferred size
        return table;
    }

    public static JTable createAddRsTable(Add_RS[] _ADD_RS) {
//        String id;
//        String opcode;
//        String vj;
//        String vk;
//        String qj;
//        String qk;
//        boolean busy;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Opcode");
        model.addColumn("Vj");
        model.addColumn("Vk");
        model.addColumn("Qj");
        model.addColumn("Qk");
        model.addColumn("Busy");

        // Populate the table with data from the array
        for (Add_RS addRs : _ADD_RS) {
            model.addRow(new Object[]{
                    addRs.id,
                    addRs.opcode,
                    addRs.vj,
                    addRs.vk,
                    addRs.qj,
                    addRs.qk,
                    addRs.busy
            });
        }
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize()); // Set preferred size
//        adjustColumnWidths(table); // Adjust column widths method has not been made

        return table;

    }

    public static JTable createMulRsTable(Mul_RS[] _Mul_RS ) {
//        String id;
//        String opcode;
//        String vj;
//        String vk;
//        String qj;
//        String qk;
//        boolean busy;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Opcode");
        model.addColumn("Vj");
        model.addColumn("Vk");
        model.addColumn("Qj");
        model.addColumn("Qk");
        model.addColumn("Busy");

        // Populate the table with data from the array
        for (Mul_RS mulRs : _Mul_RS) {
            model.addRow(new Object[]{
                    mulRs.id,
                    mulRs.opcode,
                    mulRs.vj,
                    mulRs.vk,
                    mulRs.qj,
                    mulRs.qk,
                    mulRs.busy
            });
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize()); // Set preferred size
        return table;
    }

    public static JTable createLoadRsTable(Load_RS[] _Load_RS) {
//        String id;
//        String opcode;
//        int address;
//        boolean busy;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Opcode");
        model.addColumn("Address");
        model.addColumn("Busy");

        // Populate the table with data from the array
        for (Load_RS loadRs : _Load_RS) {
            model.addRow(new Object[]{
                    loadRs.id,
                    loadRs.opcode,
                    loadRs.address == - 1 ? null : loadRs.address,
                    loadRs.busy
            });
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize()); // Set preferred size
        return table;
    }

    public static JTable createStoreRsTable(Store_RS[] _Store_RS) {
//        String id;
//        String opcode;
//        String vj;
//        String qj;
//        int address;
//        boolean busy;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Opcode");
        model.addColumn("Vj");
        model.addColumn("Qj");
        model.addColumn("Address");
        model.addColumn("Busy");

        // Populate the table with data from the array
        for (Store_RS storeRs : _Store_RS) {
            model.addRow(new Object[]{
                    storeRs.id,
                    storeRs.opcode,
                    storeRs.vj,
                    storeRs.qj,
                    storeRs.address == -1 ? null : storeRs.address ,
                    storeRs.busy
            });
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize()); // Set preferred size
        return table;
    }

    public static JTable createRegFileIntegerTable(String[] dataArray) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("REG NUM");
        model.addColumn("VALUE");

        // Populate the table with data from the array
        int i=0;
        for (String data : dataArray) {
            model.addRow(new Object[]{i++,data});
        }

        JTable table = new JTable(model);
        Dimension preferredSize = new Dimension(table.getPreferredSize().width, 150); // Set the desired size
        table.setPreferredScrollableViewportSize(preferredSize); // Set preferred size
        return table;
    }

    public static JTable createRegFileFloatTable(String[] dataArray) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("REG NUM");
        model.addColumn("VALUE");

        // Populate the table with data from the array
        int i=0;
        for (String data : dataArray) {
            model.addRow(new Object[]{i++,data});
        }

        JTable table = new JTable(model);
        Dimension preferredSize = new Dimension(table.getPreferredSize().width, 150); // Set the desired size
        table.setPreferredScrollableViewportSize(preferredSize); // Set preferred size
        return table;
    }

    public static JTable createCacheTable(double[] dataArray) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ADDRESS");
        model.addColumn("VALUE");

        // Populate the table with data from the array
        int i=0;
        for (double data : dataArray) {
            model.addRow(new Object[]{i++,data});
        }

        JTable table = new JTable(model);
        Dimension preferredSize = new Dimension(table.getPreferredSize().width, 150); // Set the desired size
        table.setPreferredScrollableViewportSize(preferredSize); // Set preferred size
        return table;
    }



}
