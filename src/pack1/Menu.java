package pack1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Menu implements MouseListener{
  
    public static void render(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt = new Font("Calligrapher", Font.BOLD, 40);
        
        g.setColor(Color.DARK_GRAY);
        
        g.fill3DRect(440, 210, 100, 70, true);
        g.fill3DRect(440, 297, 100, 70, true);
        g.fill3DRect(440, 384, 100, 70, true);
        
        g.setColor(Color.BLUE);
        g.setFont(fnt);
        
        
        
        g.drawString("Play", 440 + 10, 210 + 45);
        g2d.draw3DRect(440, 210, 100, 70,true);
        
        g.drawString("Help", 440 + 8, 297 + 45);
        g2d.draw3DRect(440, 297, 100, 70, true);
        
        g.drawString("Quit", 440 + 8, 384 + 45);
        g2d.draw3DRect(440, 384, 100, 70, true);
            

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int mx = e.getX();
        int my = e.getY();
        
        if(mx>=440 && mx<=540) {  
            
            if(my>=210 && my<=280) {
              
                Windowset.State = Windowset.STATE.GAME;
            }

            if(my>=297 && my<=367) {
                
                //HelpWindow ob2=new HelpWindow(); ///goes to help window
                Windowset.State = Windowset.STATE.HELP;
            }
  
            if(my>=384 && my<=454) {
                
                System.exit(1);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    
}