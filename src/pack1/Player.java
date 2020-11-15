package pack1;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
public class Player{
    
        int nx,nx2, dy, pause = 0;
        static int dx = 0;
        Image moving;
        boolean firstpress = false;
       // boolean lefttpress = false;
        int corx, cory, shift,ammo=10; 
          ImageIcon m =  new ImageIcon("src\\resource\\player2.gif");
         ImageIcon j= new ImageIcon("src\\resource\\player2_still.png");
         static ArrayList bullets;
        
           public Player() {
        
        	nx2=990;
        	shift = 0;
        	corx = 0;
        	cory = 400;
                moving = m.getImage();
                bullets = new ArrayList();
       
        }
        public static ArrayList getBullets()
        {
                return bullets;
        }
        
        public void fire()
        {
                if (ammo > 0)
                {
                ammo--;
                //The v is from the board class, which corresponds to the character's
                //position when it is jumping, resulting in the bullet being formed
                //at a higher position when the character is at the peak of its jump
                        Bullet z = new Bullet(280, (Windowset.v + 40));
                bullets.add(z);
        }}
        public int getCoorx() {
            
        	return corx;
        }
        
        public int getCoory() {
            
        	return cory;
        }
       
        public void move() {
            
        	corx -= dx;
        	shift +=dx; 
        	nx2+=dx;
        	nx+=dx;
        	
        }
 
 
        public Image getImage() {
            
                return moving;
        }
        
        public int getShift() {
        	return shift;
        }
     
//        public int getPauseValue() {
//        	return pause;
//        }
//        
        public void keyPressed(KeyEvent e) {
        	
                int key = e.getKeyCode();
                if(key==KeyEvent.VK_LEFT)
                {
                	fire();
                }
                if ( key == KeyEvent.VK_RIGHT){ 
                    
                	if(firstpress == false) {
                            
                		dx = 2;
                		firstpress = true;
                	}
                	moving = m.getImage();          
                }
               
                if (key == KeyEvent.VK_UP){
                   
                        dy = 1;    
                        moving = m.getImage();
                }    
                
                if(key == KeyEvent.VK_SPACE) {
                    
                	
                	dx=0;
                	moving=j.getImage();
                	
                }
                
                if(key == KeyEvent.VK_ENTER) {
                    
                	dx=2; 
                }
           }
 
         public void keyReleased(KeyEvent e) {
        			
                int key = e.getKeyCode();
 
                if (key == KeyEvent.VK_UP){
                    
                	dy = 0;
                        
                        moving = m.getImage();
                }
               if (key == KeyEvent.VK_RIGHT){
                    
                	dx = 0;
                	firstpress=false;
                        
                        moving = m.getImage();
                }
         }
 }
