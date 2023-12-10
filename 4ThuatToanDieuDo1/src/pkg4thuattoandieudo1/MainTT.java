/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4thuattoandieudo1;

/**
 *
 * @author FPTSHOP
 */
import java.util.ArrayList;
import java.util.List;

public abstract class MainTT
{
    private final List<Process> ps;
    private final List<XuLy> timeline;
    private int timeQuantum;
    
    public MainTT()
    {
        ps = new ArrayList();
        timeline = new ArrayList();
        timeQuantum = 1;
    }
    
    public boolean add(Process p)
    {
        return ps.add(p);
    }
    
    public void setTimeQuantum(int timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }
    
    public int getTimeQuantum()
    {
        return timeQuantum;
    }
    
    public double getAverageWaitingTime()
    {
        double avg = 0.0;
        
        for (Process p : ps)
        {
            avg += p.getWaitingTime();
        }
        
        return avg / ps.size();
    }
    
    public double getAverageTurnAroundTime()
    {
        double avg = 0.0;
        
        for (Process p : ps)
        {
            avg += p.getTurnaroundTime();
        }
        
        return avg / ps.size();
    }
    
    public XuLy getEvent(Process p)
    {
        for (XuLy e : timeline)
        {
            if (p.getName().equals(e.getProcessName()))
            {
                return e;
            }
        }
        
        return null;
    }
    
    public Process getRow(String process)
    {
        for (Process p : ps)
        {
            if (p.getName().equals(process))
            {
                return p;
            }
        }
        
        return null;
    }
    
    public List<Process> getRows()
    {
        return ps;
    }
    
    public List<XuLy> getTimeline()
    {
        return timeline;
    }
    
    public abstract void process();
}
