/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4thuattoandieudo1;

/**
 *
 * @author FPTSHOP
 */
public class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;
    int priorityLevel;

    public Process(String name, int arrivalTime, int burstTime, int completionTime, int turnaroundTime, int waitingTime, int priorityLevel) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.completionTime = completionTime;
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
        this.priorityLevel = priorityLevel;
    }

    public Process(String name, int arrivalTime, int burstTime, int priorityLevel) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityLevel = priorityLevel;
        this.completionTime = 0;
        this.turnaroundTime = 0;
        this.waitingTime = 0;
    }

    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityLevel = 0;
        this.completionTime = 0;
        this.turnaroundTime = 0;
        this.waitingTime = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getName() {
        return this.name;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getBurstTime() {
        return this.burstTime;
    }

    public Object getCompletionTime() {
        return this.completionTime;
    }

    public int getTurnaroundTime() {
        return this.turnaroundTime;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }

    public int getPriorityLevel() {
        return this.priorityLevel;
    }
    @Override
    public String toString() {
        return "Process{" + "processName=" + name + ", arrivalTime=" + arrivalTime + ", burstTime=" + burstTime + ", priorityLevel=" + priorityLevel + ", waitingTime=" + waitingTime + ", turnaroundTime=" + turnaroundTime + ", completionTime=" + completionTime + '}';
    }

    

}