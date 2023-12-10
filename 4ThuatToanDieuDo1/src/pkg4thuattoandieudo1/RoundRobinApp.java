/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4thuattoandieudo1;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author FPTSHOP
 */
public class RoundRobinApp extends JFrame{
    private JLabel lb_AT;
    private JLabel lb_BT;
    private JLabel avgWaitingTimeLabel1;
    private JLabel ganttChartLabel1;
    private JLabel timeQuantumLabel;
    private JTextField arrivalTextField1;
    private JTextField burstTextField1;
    private JTextField timeQuantumTextField;
    private JButton addButton1;
    private JButton calculateButton1;
    private JTable resultTable1;
    private DefaultTableModel tableModel1;
    private GanttChartPanel ganttChartPanel1;
    private Process[] processes;
    
    public RoundRobinApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setTitle("RR Scheduling Algorithm");

        lb_AT = new JLabel("Arrival Time: ");
        lb_AT.setBounds(20, 30, 80, 20);
        lb_BT = new JLabel("Burst Time: ");
        lb_BT.setBounds(20, 65, 80, 20);
        timeQuantumLabel = new JLabel("Time Quantum: ");
        timeQuantumLabel.setBounds(20, 95, 100, 20);
        timeQuantumTextField = new JTextField(10);
        timeQuantumTextField.setBounds(110, 95, 200, 30);
        arrivalTextField1 = new JTextField(10);
        arrivalTextField1.setBounds(110, 30, 200, 30);
        burstTextField1 = new JTextField(10);
        burstTextField1.setBounds(110, 65, 200, 30);
        addButton1 = new JButton("Add Process");
        addButton1.setBounds(360, 40, 130, 40);
        calculateButton1 = new JButton("Calculate");
        calculateButton1.setBounds(560, 40, 130, 40);
        ganttChartPanel1 = new GanttChartPanel();
        ganttChartPanel1.setBounds(20, 420, 900, 90);
        
        avgWaitingTimeLabel1 = new JLabel("Average Waiting Time: ");
        avgWaitingTimeLabel1.setBounds(20, 360, 200, 20);

        ganttChartLabel1 = new JLabel("Gantt Chart:");
        ganttChartLabel1.setBounds(20, 390, 200, 20);
        
        tableModel1 = new DefaultTableModel();
        resultTable1 = new JTable(tableModel1);
        tableModel1.addColumn("Process");
        tableModel1.addColumn("Arrival Time");
        tableModel1.addColumn("Burst Time");
        tableModel1.addColumn("Completion Time");
        tableModel1.addColumn("Turnaround Time");
        tableModel1.addColumn("Waiting Time");

        JScrollPane tableScrollPane = new JScrollPane(resultTable1);
        tableScrollPane.setBounds(20, 130, 900, 200);

        
        setLayout(null);

        
        add(lb_AT);
        add(arrivalTextField1);
        add(lb_BT);
        add(avgWaitingTimeLabel1);
        add(ganttChartLabel1);
        add(burstTextField1);
        add(addButton1);
        add(calculateButton1);
        add(tableScrollPane);
        add(ganttChartPanel1);
        add(timeQuantumLabel);
        add(timeQuantumTextField);
        // Add action listeners
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProcess1();
            }
        });

        calculateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTimes();
            }
        });
        
        processes = new Process[0]; 
        setVisible(true);
    }
    private void addProcess1() {
        try {
            int arrivalTime = Integer.parseInt(arrivalTextField1.getText());
            int burstTime = Integer.parseInt(burstTextField1.getText());

            Process process = new Process("P" + (processes.length + 1), arrivalTime, burstTime);

            
            processes = Arrays.copyOf(processes, processes.length + 1);
            processes[processes.length - 1] = process;

            arrivalTextField1.setText("");
            burstTextField1.setText("");

            
            tableModel1.addRow(new Object[]{process.name, process.arrivalTime, process.burstTime, 0, 0, 0});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculateTimes(){
        MainTT cpuRR;
        cpuRR = new RR();
        cpuRR.setTimeQuantum(Integer.parseInt(timeQuantumTextField.getText()));
        
        for (int i = 0; i < tableModel1.getRowCount(); i++){
                String process = (String) tableModel1.getValueAt(i, 0);
                int at = (int) tableModel1.getValueAt(i, 1);
                int bt =( int) tableModel1.getValueAt(i, 2);  
        cpuRR.add(new Process(process, at, bt));
        }
        cpuRR.process();
        
        for (int i = 0; i < tableModel1.getRowCount(); i++) {
            String proc = (String) tableModel1.getValueAt(i, 0);
            Process p = cpuRR.getRow(proc);
            tableModel1.setValueAt(p.getCompletionTime(), i, 3);
            tableModel1.setValueAt(p.getTurnaroundTime(), i, 4);
            tableModel1.setValueAt(p.getWaitingTime(), i, 5);
        }
        avgWaitingTimeLabel1.setText("Average Waiting Time: " + cpuRR.getAverageWaitingTime());
        ganttChartPanel1.setChartProcess(cpuRR.getTimeline());
    }

    
}

