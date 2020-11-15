package pack1;

import java.awt.*;
import javax.swing.ImageIcon;
 
public class Obstacle {
 
        Image rock;
        int x, y;
        boolean isAlive = true;
       
        public Obstacle(int X, int Y, String loc)
        {
                x = X;
                y = Y;
                ImageIcon i = new ImageIcon(loc);
                rock = i.getImage();
        }
       
        public int getX()
        {
                return x;
        }
        public int getY()
        {
                return y;
        }
        public boolean Alive()
        {
                return isAlive;
        }
        public Image getImage()
        {
                return rock;
        }
       
        public void move(int dx){
       
                x -= dx;
        }
 
        public Rectangle getBounds()
        {
                return new Rectangle(x ,y ,30, 30); 
        }
       
}