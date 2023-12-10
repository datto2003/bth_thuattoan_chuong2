
package pkg4thuattoandieudo1;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FPTSHOP
 */
public class RR extends MainTT{

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
        int timeQuantum = this.getTimeQuantum();
        
        for(Process row: rows){
            System.out.println(row.toString());
        }
        while (!rows.isEmpty())
        {
            Process row = rows.get(0);
            if(row.getArrivalTime() > time){
                time += row.getArrivalTime();
            }
            int bt = (row.getBurstTime() < timeQuantum ? row.getBurstTime() : timeQuantum);
            this.getTimeline().add(new XuLy(row.getName(), time, time + bt));
            time += bt;
            
            int timeTmp = time;
            
            if (row.getBurstTime() > timeQuantum)
            {
                
                row.setBurstTime(row.getBurstTime() - timeQuantum);
                
                for (int i = 0; i < rows.size(); i++)
                {
                    timeTmp += rows.get(i).getArrivalTime();
                    if (time > timeTmp)
                    {
                        rows.add(i, row);
                        
//                        System.out.println(rows.get(i).toString());
                        break;
                    }else{
                       if (i == rows.size() - 1)
                        {
                            rows.add(row);
                            break;
                        }
                    }
                    
                }
            }
            rows.remove(0);
                
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
