package pack1;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
 
public class Windowset extends JPanel implements ActionListener, Runnable {
    
    JFrame f;
  
private static final long serialVersionUID = 1L;
    Player p;
    public Image backimg,menuBg,helpBg,Overbg,exitBg,lostpl;
    Timer time;
    static int v = 420;
    private Menu Menu;
    private HelpWindow helpi;
   private Over over;
    Thread animator;
   int totalScore=0;
   public static int life=3	   ;
   public static int lifeval=0;
    public static final int N = 64000;
    Enemy en;
    Enemy en2;
    Enemy en3;
    public static int SCORE=0;
    Obstacle obs;
    Diamond cn;
    ArrayList < Obstacle > arr = new ArrayList < Obstacle >();
    ArrayList < Diamond > brr = new ArrayList < Diamond > ();
    ArrayList < Bullet > bt = new ArrayList < Bullet> ();
    boolean lost = false, a = false, done2 = false;
    public static enum STATE {MENU,GAME,HELP,OVER}; 
    public static STATE State = STATE.MENU;
   
    static Font font = new Font("Calligrapher", Font.BOLD, 25);
    
    static Font font1 = new Font("Calligrapher", Font.BOLD, 40);
    
    public Windowset() {
    	
    	this.addMouseListener(new Menu()); 
             
    	 this.addMouseListener(new HelpWindow());
        	p = new Player();  
            Menu = new Menu();
           
            addKeyListener(new actionListener());
            setFocusable(true); 
            
            ImageIcon i1 = new ImageIcon("src\\resource\\ba.jpg");             
            menuBg = i1.getImage();
            
            ImageIcon i2 = new ImageIcon("src\\resource\\bg.jpg");
            backimg = i2.getImage();
            
            ImageIcon i3=new ImageIcon("src\\resource\\Intro11.jpg");
            helpBg=i3.getImage();
            
           // ImageIcon i4=new ImageIcon("E:\\my_Game\\src\\my_game\\res\\forestpurple.jpg");
           // exitBg=i4.getImage();
            
           // ImageIcon i5=new ImageIcon("E:\\my_Game\\src\\my_game\\res\\gameover.gif");
            //Overbg=i5.getImage();
            
           // ImageIcon i6=new ImageIcon("E:\\my_Game\\src\\my_game\\res\\lost.gif");
           // lostpl=i6.getImage();
            
            
            time = new Timer(10, this); 
            time.start();
            en=new Enemy(1000,450,"src//resource//redghost22.png");
            en2=new Enemy(1000,450,"src//resource//redghost22.png");
            en3=new Enemy(1000,450,"src//resource//redghost22.png");
           int counter = 0;       
            for(int k = 4500 ; k <= N ; k += 500, counter++) {
            	 counter %= 4;
            	 if(counter == 3) {
            		 continue;
            	 }
            	 if(counter == 0) {
            		 	arr.add(new Obstacle(k,490,"src\\resource\\obstacles.png"));
            		 	arr.add(new Obstacle(k+50,490,"src\\resource\\obstacles.png"));
            		 	continue;
            	 }
            	 obs = new Obstacle(k + 100, 490, "src\\resource\\obstacles.png");
            	 arr.add(obs);
  
            }
            
                       
            for(int k = 1000 ; k <= 1800 ; k += 200) {
            		if(k%900 == 0) {
            			cn = new Diamond(k,470,"src\\resource\\Red diamond.gif");
            		}
            		else cn = new Diamond(k,430,"src\\resource\\Red diamond.gif");
            		brr.add(cn);
            }
            
            for(int k = 2000 ; k <= N ; k += 500) {
            		Random rand = new Random();
            		int tot = rand.nextInt(100);
         		tot %= 2;
           		tot ^= 1;
            		if(tot == 1) {
            			cn = new Diamond(k+200, 430, "src\\resource\\Red diamond.gif");
            			brr.add(cn);
            			cn = new Diamond(k+300,470,"src\\resource\\Red diamond.gif");
            		}
            		else {
            			cn = new Diamond(k+300, 450, "src\\resource\\Red diamond.gif");
            			brr.add(cn);
            			cn = new Diamond(k+200,430,"src\\resource\\Red diamond.gif");
            		}
            		brr.add(cn);	
            }
            
  }

    
    public void actionPerformed(ActionEvent e) {
        checkCollisions();
        checkCollisions2();
        ArrayList bullets = Player.getBullets();
        for (int w = 0; w < bullets.size(); w++)
        {
                Bullet m = (Bullet) bullets.get(w);
                if (m.getVisible() == true)
                m.move();
                else
                        bullets.remove(w);
        }
          
            p.move();
            if(p.shift>400)
            	en.move(Player.dx,p.getShift());
            if(p.shift>700)
            	en2.move(Player.dx,p.getShift());
            if(p.shift>900)
            	en3.move(Player.dx,p.getShift());
            for(int k = 0 ;k < arr.size() ; k++) {
                
            		if(p.getShift() >= 1000) {
                                
            			arr.get(k).move(p.dx); 
            		}
            		if(arr.get(k).getX() <= -20) {
                                
            			arr.remove(k);
            		} 
            } 
            
            for(int k = 0 ; k < brr.size(); k++) {
                
            		if(p.getShift() >= 500) {
                                
            			brr.get(k).move(p.dx);
            		}
            }
            
            repaint();
    }
    
    public void checkCollisions2()
	{
		 
    	 Rectangle r1 = en.getBounds();
         Rectangle r2 = en2.getBounds();
         Rectangle r3 = en3.getBounds();
         ArrayList bullets = Player.getBullets();
         for (int w = 0; w < bullets.size(); w++)
         {
                 Bullet m = (Bullet) bullets.get(w);
                 Rectangle m1 = m.getBounds();
                 if (r1.intersects(m1) && en.Alive())
                 {
                         en.isAlive = false;
                         m.visible = false;
                 }
                 else if (r2.intersects(m1)&& en2.Alive())
                 {
                         en2.isAlive = false;
                         m.visible = false;
                 }
                 else if (r3.intersects(m1)&& en3.Alive())
                 {
                         en3.isAlive = false;
                         m.visible = false;
                 }
         }
        
         Rectangle d= new Rectangle(150 ,(420 + (420 - v)) , 89, 89);
         if (d.intersects(r1))
                 {lost = true;	
                  lifeval--;
                   }  
         if (d.intersects(r2) )
         {lost = true;	
          lifeval--;
           }  
         
         if (d.intersects(r3) )
         {lost = true;	
          lifeval--;
           }                             
			}
	public void checkCollisions()
	{
		 
			Rectangle player = new Rectangle(150 ,(420 + (420 - v)) , 89, 89);
			
			for(int k = 0 ; k < arr.size() ; k++) {
                                
					Rectangle obst = arr.get(k).getBounds();
					
					if(player.intersects(obst)) {
							//System.out.println("Collision occured!");
							lost = true;
                                                            
                                                            lifeval--;
                                                            
                                                           
				}
                                          
                                         
			}
			
			for(int k = 0 ; k < brr.size(); k++) {
					Rectangle diamond = brr.get(k).getBounds();
					if(player.intersects(diamond)) {
						brr.get(k).endLife();
						///totalScore = 0;
//                                                    //lost = true;
//                                                    //lifeval--;
					}
			}
	}

         
    public void paint(Graphics g) {
        
        	super.paint(g);
                Graphics2D g2d = (Graphics2D) g;
                    
    		if (lost) { /// After collision!
              
if(life==0){
          
          p.dx=0;
       
         
         Windowset.State = Windowset.STATE.OVER;
          
      }
      

      lost = false;
      
}
	        
		if (p.dy == 1 && done2 == false) { 
                        
                          
		        done2 = true;
		        animator = new Thread(this);
		        animator.start();
		}
 
                
                
                if(State == STATE.GAME) {
                	if((p.getShift()-200)%2400==0) p.nx=0;
                	if((p.getShift()-1500)%2400==0) p.nx2=0;
                	//g2d.drawImage(backimg,p.getCoorx(),0,null);
                	   g2d.drawImage(backimg,990-p.nx2,0,null);
                               // System.out.println(p.getShift());
                	   if(p.getShift()>=300)
                	    	g2d.drawImage(backimg,990-p.nx,0,null);
                	    g2d.drawImage(p.getImage(), 150, v, null);  
                	    ArrayList bullets=Player.getBullets();
                	    for(int w=0;w<bullets.size();w++) {
                	    	Bullet m=(Bullet)bullets.get(w);
                	    	g2d.drawImage(m.getImage(),m.getX(),m.getY(),null);
                	    }
                	    	g2d.setFont(font);
                            g2d.setColor(Color.BLUE);
                            g2d.drawString("Ammo left: " + p.ammo, 500, 20);
                            if (p.shift > 400)
                                    if (en.Alive() == true)
                                            g2d.drawImage(en.getImage(), en.getX(), en.getY(), null);
                            if (p.shift > 700)
                                    if (en2.Alive() == true)
                                            g2d.drawImage(en2.getImage(), en2.getX(), en2.getY(), null);
                            if (p.shift > 900)
                            if (en3.Alive() == true)
                                g2d.drawImage(en3.getImage(), en3.getX(), en3.getY(), null);
                	    
               
                }
                else if(State==STATE.MENU){
                            
                        g.drawImage(menuBg, 0, 0, null);
                            
                        Menu.render(g);
                }

                else if(State==STATE.HELP){
                    
                    g.drawImage(helpBg, 0, 0, null);
                        
                    helpi.render(g);
            }         
                
                else if(State==STATE.OVER){
                        
                    g2d.drawImage(backimg,0,0,null);
                        
                    Over.render(g);
            }
        
                g2d.setFont(font);
                    
                g2d.setColor(Color.cyan);
                    
                int score = 0;
                    
                    boolean q=false;
                    for(int k = 0 ; k < brr.size(); k++) {
               		if(!brr.get(k).isAlive()) {
                			score++;
                		}
                }
                    
                   SCORE=score;
                    
                g2d.drawString("Score: " + score , 850, 24);/// Show tot as 0!
                   
                    g2d.setColor(Color.magenta);
                    
                    g2d.drawString("Life: " + life , 850, 60);
                    
                    //System.out.println("lifeval "+lifeval);
                  
                    if(lifeval==-118 || lifeval==-236){
                        
                        --life;
                        
                        q=true;
                        
                        lifeval=0;
                        
                        if(life<=0)
                            life=0;                    }
          
                for(int k = 0 ; k < arr.size() ; k++) {
                        
                	if(p.getShift() >= 700) {
                		g2d.drawImage(arr.get(k).getImage(), arr.get(k).getX(), arr.get(k).getY(), null);
                	}
                }
                
                for(int k = 0 ; k < brr.size(); k++) {
                	if(brr.get(k).isAlive() && p.getShift() >= 700) {
                		g2d.drawImage(brr.get(k).getImage(), brr.get(k).getX(), brr.get(k).getY(), null);
                	}
                }
    }

    private class actionListener extends KeyAdapter {
        
            public void keyReleased(KeyEvent e) {
                    p.keyReleased(e);
            }

            public void keyPressed(KeyEvent e) {
                    p.keyPressed(e);
            }
    }

    boolean h = false;
    boolean done = false;

    public void jumpMove() {

            if (h == false)
                    v -= 1;
            if (v == 280)
                    h = true;
            if (h == true && v <= 420) {
                    v += 1;
                    if (v == 420) {
                            done = true;
                    }
            }
    }

    
    public void run() {

            long beforeTime, timeDiff, sleep;

            beforeTime = System.currentTimeMillis();

            while (done == false) {

                    jumpMove();

                    timeDiff = System.currentTimeMillis() - beforeTime;
                    sleep = 10 - timeDiff;

                    if (sleep < 0)  sleep = 2;
                    
                    try {
                            Thread.sleep(sleep);
                            
                    } catch (InterruptedException e) {
                    	
                    }

                    beforeTime = System.currentTimeMillis();
            }
            
            done = false;
            h = false;
            done2 = false;
    }
}
