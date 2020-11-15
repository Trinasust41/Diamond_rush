package pack1;
import java.awt.*;
import javax.swing.*;
 
public class Bullet {
        int x, y;//-- Controls the CURRENT location of THIS bullet
        //Each object of this class is a new BULLET
         Image img;
        boolean visible;
        //sets weather THIS bullet is visible or not
 
       
        public int getX()
        {
                return x;
        }
 
        public boolean getVisible()
        {
                return visible;
        }
       
        public int getY()
        {
                return y;
        }
       
        public Image getImage()
        {
                return img;
        }
       
        public Bullet(int startX, int startY)
        {
                ImageIcon newBullet = new ImageIcon("src//resource//bullet.png");
                img = newBullet.getImage();
                x = startX;
                y = startY;
                visible = true;
        }
        //Just like the move class in Dude class...
        public void move(){
                x = x +2; //x + bullet speed
                if (x>1000)// if x > board width
                        //Make the bullet invisible
                        visible = false;
        }
        public void setVisible(boolean isVisible) {
        	visible=isVisible;
        }
        public Rectangle getBounds() {
        	return new Rectangle (x,y,65,65);
        }
}
