
package pkg4thuattoandieudo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;




public class SJF_App extends JFrame{
    private JLabel lb_SJF;
    private JLabel lb_SJF2;
    private JLabel avgWaitingTimeLabelSJF;
    private JLabel ganttChartLabelSJF;
    private JTextField arrivalTextFieldSJF;
    private JTextField burstTextFieldSJF;
    private JButton addButtonSJF;
    private JButton calculateButtonSJF;
    private JTable resultTableSJF;
    private DefaultTableModel tableModelSJF;
    private GanttChartPanel ganttChartPanelSJF;
    private Process[] processes;
    private int n;
    public SJF_App() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setTitle("SJF Scheduling Algorithm");

        lb_SJF = new JLabel("Arrival Time: ");
        lb_SJF.setBounds(20, 30, 80, 20);
        lb_SJF2 = new JLabel("Burst Time: ");
        lb_SJF2.setBounds(20, 65, 80, 20);
        arrivalTextFieldSJF = new JTextField(10);
        arrivalTextFieldSJF.setBounds(110, 30, 200, 30);
        burstTextFieldSJF = new JTextField(10);
        burstTextFieldSJF.setBounds(110, 65, 200, 30);
        addButtonSJF = new JButton("Add Process");
        addButtonSJF.setBounds(360, 40, 130, 40);
        calculateButtonSJF = new JButton("Calculate");
        calculateButtonSJF.setBounds(560, 40, 130, 40);
        ganttChartPanelSJF = new GanttChartPanel();
        ganttChartPanelSJF.setBounds(20, 420, 900, 90);
        
        avgWaitingTimeLabelSJF = new JLabel("Average Waiting Time: ");
        avgWaitingTimeLabelSJF.setBounds(20, 360, 200, 20);

        ganttChartLabelSJF = new JLabel("Gantt Chart:");
        ganttChartLabelSJF.setBounds(20, 390, 200, 20);
        
        tableModelSJF = new DefaultTableModel();
        resultTableSJF = new JTable(tableModelSJF);
        tableModelSJF.addColumn("Process");
        tableModelSJF.addColumn("Arrival Time");
        tableModelSJF.addColumn("Burst Time");
        tableModelSJF.addColumn("Completion Time");
        tableModelSJF.addColumn("Turnaround Time");
        tableModelSJF.addColumn("Waiting Time");

        JScrollPane tableScrollPane = new JScrollPane(resultTableSJF);
        tableScrollPane.setBounds(20, 120, 900, 200);

        // Set layout
        setLayout(null);

        // Add components to the frame
        add(lb_SJF);
        add(arrivalTextFieldSJF);
        add(lb_SJF2);
        add(avgWaitingTimeLabelSJF);
        add(ganttChartLabelSJF);
        add(burstTextFieldSJF);
        add(addButtonSJF);
        add(calculateButtonSJF);
        add(tableScrollPane);
        add(ganttChartPanelSJF);

        // Add action listeners
        addButtonSJF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProcess();
            }
        });

        calculateButtonSJF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTimes();
              
            }
        });

        processes = new Process[0]; 

        setVisible(true);
    }

    private void addProcess() {
        try {
            int arrivalTime = Integer.parseInt(arrivalTextFieldSJF.getText());
            int burstTime = Integer.parseInt(burstTextFieldSJF.getText());

            Process process = new Process("P" + (processes.length + 1), arrivalTime, burstTime);

            
            processes = Arrays.copyOf(processes, processes.length + 1);
            processes[processes.length - 1] = process;

            arrivalTextFieldSJF.setText("");
            burstTextFieldSJF.setText("");

            
            tableModelSJF.addRow(new Object[]{process.name, process.arrivalTime, process.burstTime, 0, 0, 0});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //
    
    
    //
    private void calculateTimes() {
        MainTT cpuRR;
        cpuRR = new ShortestJobFirst();
        for (int i = 0; i < tableModelSJF.getRowCount(); i++){
                String process = (String) tableModelSJF.getValueAt(i, 0);
                int at = (int) tableModelSJF.getValueAt(i, 1);
                int bt =( int) tableModelSJF.getValueAt(i, 2);  
        cpuRR.add(new Process(process, at, bt));
        }
        cpuRR.process();
        
        for (int i = 0; i < tableModelSJF.getRowCount(); i++) {
            String proc = (String) tableModelSJF.getValueAt(i, 0);
            Process p = cpuRR.getRow(proc);
            tableModelSJF.setValueAt(p.getCompletionTime(), i, 3);
            tableModelSJF.setValueAt(p.getTurnaroundTime(), i, 4);
            tableModelSJF.setValueAt(p.getWaitingTime(), i, 5);
        }
        avgWaitingTimeLabelSJF.setText("Average Waiting Time: " + cpuRR.getAverageWaitingTime());
        ganttChartPanelSJF.setChartProcess(cpuRR.getTimeline());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SJF_App());
    }
}