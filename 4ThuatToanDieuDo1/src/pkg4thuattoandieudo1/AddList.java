/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4thuattoandieudo1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FPTSHOP
 */
public class AddList {
    public static List<Process> deepCopy(List<Process> oldList)
    {
        List<Process> newList = new ArrayList();
        
        for (Process p : oldList)
        {
            newList.add(new Process(p.getName(), p.getArrivalTime(), p.getBurstTime(), p.getPriorityLevel()));
        }
        
        return newList;
    }
}
