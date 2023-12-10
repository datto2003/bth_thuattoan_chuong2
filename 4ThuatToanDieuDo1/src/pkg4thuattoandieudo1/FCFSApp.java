/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4thuattoandieudo1;

/**
 *
 * @author FPTSHOP
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;




public class FCFSApp extends JFrame{
    private JLabel lb_1;
    private JLabel lb_2;
    private JLabel avgWaitingTimeLabel;
    private JLabel ganttChartLabel;
    private JTextField arrivalTextField;
    private JTextField burstTextField;
    private JButton addButton;
    private JButton calculateButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private GanttChartPanel ganttChartPanel;
    private Process[] processes;

    public FCFSApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setTitle("FCFS Scheduling Algorithm");

        lb_1 = new JLabel("Arrival Time: ");
        lb_1.setBounds(20, 30, 80, 20);
        lb_2 = new JLabel("Burst Time: ");
        lb_2.setBounds(20, 65, 80, 20);
        arrivalTextField = new JTextField(10);
        arrivalTextField.setBounds(110, 30, 200, 30);
        burstTextField = new JTextField(10);
        burstTextField.setBounds(110, 65, 200, 30);
        addButton = new JButton("Add Process");
        addButton.setBounds(360, 40, 130, 40);
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(560, 40, 130, 40);
        ganttChartPanel = new GanttChartPanel();
        ganttChartPanel.setBounds(20, 420, 300, 90);
        
        avgWaitingTimeLabel = new JLabel("Average Waiting Time: ");
        avgWaitingTimeLabel.setBounds(20, 360, 200, 20);

        ganttChartLabel = new JLabel("Gantt Chart:");
        ganttChartLabel.setBounds(20, 390, 200, 20);
        // Initialize table and its model
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        tableModel.addColumn("Process");
        tableModel.addColumn("Arrival Time");
        tableModel.addColumn("Burst Time");
        tableModel.addColumn("Completion Time");
        tableModel.addColumn("Turnaround Time");
        tableModel.addColumn("Waiting Time");

        JScrollPane tableScrollPane = new JScrollPane(resultTable);
        tableScrollPane.setBounds(20, 120, 900, 200);

        // Set layout
        setLayout(null);

        // Add components to the frame
        add(lb_1);
        add(arrivalTextField);
        add(lb_2);
        add(avgWaitingTimeLabel);
        add(ganttChartLabel);
        add(burstTextField);
        add(addButton);
        add(calculateButton);
        add(tableScrollPane);
        add(ganttChartPanel);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProcess();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
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
            int arrivalTime = Integer.parseInt(arrivalTextField.getText());
            int burstTime = Integer.parseInt(burstTextField.getText());

            Process process = new Process("P" + (processes.length + 1), arrivalTime, burstTime);

            // Increase the size of the array and add the new process
            processes = Arrays.copyOf(processes, processes.length + 1);
            processes[processes.length - 1] = process;

            arrivalTextField.setText("");
            burstTextField.setText("");

            // Display the current processes in the table
            tableModel.addRow(new Object[]{process.name, process.arrivalTime, process.burstTime, 0, 0, 0});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateTimes() {
        MainTT cpuRR;
        cpuRR = new FCFS();
        for (int i = 0; i < tableModel.getRowCount(); i++){
                String process = (String) tableModel.getValueAt(i, 0);
                int at = (int) tableModel.getValueAt(i, 1);
                int bt =( int) tableModel.getValueAt(i, 2);  
        cpuRR.add(new Process(process, at, bt));
        }
        cpuRR.process();
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String proc = (String) tableModel.getValueAt(i, 0);
            Process p = cpuRR.getRow(proc);
            tableModel.setValueAt(p.getCompletionTime(), i, 3);
            tableModel.setValueAt(p.getTurnaroundTime(), i, 4);
            tableModel.setValueAt(p.getWaitingTime(), i, 5);
        }
        avgWaitingTimeLabel.setText("Average Waiting Time: " + cpuRR.getAverageWaitingTime());
        ganttChartPanel.setChartProcess(cpuRR.getTimeline());
    }
    

    public static void main(String[] args) {
        FCFSApp a = new FCFSApp();
        
    }

}




