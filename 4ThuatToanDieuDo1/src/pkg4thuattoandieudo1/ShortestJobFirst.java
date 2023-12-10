
package pkg4thuattoandieudo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestJobFirst extends MainTT
{
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
            this.getTimeline().add(new XuLy(row.getName(), time, time + row.getBurstTime()));
            time += row.getBurstTime();
            
            for (int i = 0; i < rows.size(); i++)
            {
                if (rows.get(i).getName().equals(row.getName()))
                {
                    rows.remove(i);
                    break;
                }
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
