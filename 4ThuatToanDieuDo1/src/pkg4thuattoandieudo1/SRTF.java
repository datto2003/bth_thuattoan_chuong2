/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4thuattoandieudo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public class SRTF extends MainTT{

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
        
        List<Process> rows = AddList.deepCopy(this.getRows());
        int time = rows.get(0).getArrivalTime();
        
        while (!rows.isEmpty())
        {
            List<Process> availableRows = new ArrayList();
            
            for (Process row : rows)
            {
                if (row.getArrivalTime() <= time)
                {
                    availableRows.add(row);
                }
            }
            
            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((Process) o1).getBurstTime() == ((Process) o2).getBurstTime())
                {
                    return 0;
                }
                else if (((Process) o1).getBurstTime() < ((Process) o2).getBurstTime())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
            
            Process row = availableRows.get(0);
            this.getTimeline().add(new XuLy(row.getName(), time, ++time));
            row.setBurstTime(row.getBurstTime() - 1);
            
            if (row.getBurstTime() == 0)
            {
                for (int i = 0; i < rows.size(); i++)
                {
                    if (rows.get(i).getName().equals(row.getName()))
                    {
                        rows.remove(i);
                        break;
                    }
                }
            }
        }
        
        for (int i = this.getTimeline().size() - 1; i > 0; i--)
        {
            List<XuLy> timeline = this.getTimeline();
            
            if (timeline.get(i - 1).getProcessName().equals(timeline.get(i).getProcessName()))
            {
                timeline.get(i - 1).setFinishTime(timeline.get(i).getFinishTime());
                timeline.remove(i);
            }
        }
        
        Map map = new HashMap();
        
        for (Process row : this.getRows())
        {
            map.clear();
            
            for (XuLy event : this.getTimeline())
            {
                if (event.getProcessName().equals(row.getName()))
                {
                    if (map.containsKey(event.getProcessName()))
                    {
                        int w = event.getStartTime() - (int) map.get(event.getProcessName());
                        row.setWaitingTime(row.getWaitingTime() + w);
                    }
                    else
                    {
                        row.setWaitingTime(event.getStartTime() - row.getArrivalTime());
                    }
                    
                    map.put(event.getProcessName(), event.getFinishTime());
                }
            }
            
            row.setTurnaroundTime(row.getWaitingTime() + row.getBurstTime());
            row.setCompletionTime(row.getArrivalTime()+row.getTurnaroundTime());
        }
    }
    
}
