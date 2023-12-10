/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4thuattoandieudo1;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author FPTSHOP
 */
public class FCFS extends MainTT{

    @Override
    public void process()
    {        
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Process) o1).getArrivalTime() == ((Process) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((Process) o1).getArrivalTime() < ((Process) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
        
        List<XuLy> timeline = this.getTimeline();
        
        for (Process row : this.getRows())
        {
            if (timeline.isEmpty())
            {
            	  timeline.add(new XuLy(row.getName(), row.getArrivalTime(), row.getArrivalTime() + row.getBurstTime()));
            }
            else
            {
                XuLy event = timeline.get(timeline.size() - 1);
                timeline.add(new XuLy(row.getName(), event.getFinishTime(), event.getFinishTime() + row.getBurstTime()));
            }
        }
        
        for (Process row : this.getRows())
        {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
            row.setCompletionTime(row.getArrivalTime()+row.getTurnaroundTime());
        }
    }
    
}
