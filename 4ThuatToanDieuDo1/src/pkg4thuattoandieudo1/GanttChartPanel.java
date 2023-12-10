/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg4thuattoandieudo1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author FPTSHOP
 */
public class GanttChartPanel extends JPanel {
    private List<XuLy> timeline;
        
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            if (timeline != null)
            {
                int width = 35;
                
                for (int i = 0; i < timeline.size(); i++)
                {
                    XuLy event = timeline.get(i);
                    int x = 30 * (i + 1);
                    int y = 20;
                    
                    g.drawRect(x, y, 30, 30);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    g.drawString(event.getProcessName(), x + 10, y + 20);
                    g.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    g.drawString(Integer.toString(event.getStartTime()), x - 5, y + 45);
                    
                    if (i == timeline.size() - 1)
                    {
                        g.drawString(Integer.toString(event.getFinishTime()), x + 27, y + 45);
                    }
                    
                   width += 35;
                }
                
                this.setPreferredSize(new Dimension(width, 75));
            }
        }
        
        public void setChartProcess(List<XuLy> timeline)
        {
            this.timeline = timeline;
            repaint();
        }
        public void setTimeLine(Process[] processes)
        {
            this.timeline = timeline;
            repaint();
        }
}
