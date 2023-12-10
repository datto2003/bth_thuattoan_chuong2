
package pkg4thuattoandieudo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;




public class SRTFApp extends JFrame{
    private JLabel lb_SRTF;
    private JLabel lb_SRTF2;
    private JLabel avgWaitingTimeLabelSRTF;
    private JLabel ganttChartLabelSRTF;
    private JTextField arrivalTextFieldSRTF;
    private JTextField burstTextFieldSRTF;
    private JButton addButtonSRTF;
    private JButton calculateButtonSRTF;
    private JTable resultTableSRTF;
    private DefaultTableModel tableModelSRTF;
    private GanttChartPanel ganttChartPanelSRTF;
    private Process[] processes;
    private int n;
    public SRTFApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setTitle("SRTF Scheduling Algorithm");

        lb_SRTF = new JLabel("Arrival Time: ");
        lb_SRTF.setBounds(20, 30, 80, 20);
        lb_SRTF2 = new JLabel("Burst Time: ");
        lb_SRTF2.setBounds(20, 65, 80, 20);
        arrivalTextFieldSRTF = new JTextField(10);
        arrivalTextFieldSRTF.setBounds(110, 30, 200, 30);
        burstTextFieldSRTF = new JTextField(10);
        burstTextFieldSRTF.setBounds(110, 65, 200, 30);
        addButtonSRTF = new JButton("Add Process");
        addButtonSRTF.setBounds(360, 40, 130, 40);
        calculateButtonSRTF = new JButton("Calculate");
        calculateButtonSRTF.setBounds(560, 40, 130, 40);
        ganttChartPanelSRTF = new GanttChartPanel();
        ganttChartPanelSRTF.setBounds(20, 420, 900, 90);
        
        avgWaitingTimeLabelSRTF = new JLabel("Average Waiting Time: ");
        avgWaitingTimeLabelSRTF.setBounds(20, 360, 200, 20);

        ganttChartLabelSRTF = new JLabel("Gantt Chart:");
        ganttChartLabelSRTF.setBounds(20, 390, 200, 20);
        // Initialize table and its model
        tableModelSRTF = new DefaultTableModel();
        resultTableSRTF = new JTable(tableModelSRTF);
        tableModelSRTF.addColumn("Process");
        tableModelSRTF.addColumn("Arrival Time");
        tableModelSRTF.addColumn("Burst Time");
        tableModelSRTF.addColumn("Completion Time");
        tableModelSRTF.addColumn("Turnaround Time");
        tableModelSRTF.addColumn("Waiting Time");

        JScrollPane tableScrollPane = new JScrollPane(resultTableSRTF);
        tableScrollPane.setBounds(20, 120, 900, 200);

        // Set layout
        setLayout(null);

        // Add components to the frame
        add(lb_SRTF);
        add(arrivalTextFieldSRTF);
        add(lb_SRTF2);
        add(avgWaitingTimeLabelSRTF);
        add(ganttChartLabelSRTF);
        add(burstTextFieldSRTF);
        add(addButtonSRTF);
        add(calculateButtonSRTF);
        add(tableScrollPane);
        add(ganttChartPanelSRTF);

        // Add action listeners
        addButtonSRTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProcess();
            }
        });

        calculateButtonSRTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTimes();
              
            }
        });

        processes = new Process[0]; // Initialize an empty array of processes

        setVisible(true);
    }

    private void addProcess() {
        try {
            int arrivalTime = Integer.parseInt(arrivalTextFieldSRTF.getText());
            int burstTime = Integer.parseInt(burstTextFieldSRTF.getText());

            Process process = new Process("P" + (processes.length + 1), arrivalTime, burstTime);

            // Increase the size of the array and add the new process
            processes = Arrays.copyOf(processes, processes.length + 1);
            processes[processes.length - 1] = process;

            arrivalTextFieldSRTF.setText("");
            burstTextFieldSRTF.setText("");

            // Display the current processes in the table
            tableModelSRTF.addRow(new Object[]{process.name, process.arrivalTime, process.burstTime, 0, 0, 0});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //
    
    
    //
    private void calculateTimes() {
        MainTT cpuRR;
        cpuRR = new SRTF();
        for (int i = 0; i < tableModelSRTF.getRowCount(); i++){
                String process = (String) tableModelSRTF.getValueAt(i, 0);
                int at = (int) tableModelSRTF.getValueAt(i, 1);
                int bt =( int) tableModelSRTF.getValueAt(i, 2);  
        cpuRR.add(new Process(process, at, bt));
        }
        cpuRR.process();
        
        for (int i = 0; i < tableModelSRTF.getRowCount(); i++) {
            String proc = (String) tableModelSRTF.getValueAt(i, 0);
            Process p = cpuRR.getRow(proc);
            tableModelSRTF.setValueAt(p.getCompletionTime(), i, 3);
            tableModelSRTF.setValueAt(p.getTurnaroundTime(), i, 4);
            tableModelSRTF.setValueAt(p.getWaitingTime(), i, 5);
        }
        avgWaitingTimeLabelSRTF.setText("Average Waiting Time: " + cpuRR.getAverageWaitingTime());
        ganttChartPanelSRTF.setChartProcess(cpuRR.getTimeline());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SRTFApp());
    }
}