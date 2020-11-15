package pack1;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
public class Frame {
	public Frame() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        
        JFrame frame = new JFrame("DIAMOND RUSH");
                        
        frame.setSize(1000,600); 
        
        frame.add(new Windowset()); 
       
        frame.setResizable(false);
        
        frame.setVisible(true);
        
        frame.setLocationRelativeTo(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AudioInputStream sample;
		sample = AudioSystem.getAudioInputStream(new File("C:\\Users\\USER\\Desktop\\New folder 3\\60-racing_car-master\\Drive_and_shoot\\src\\Resources\\rap.wav"));
		Clip clip = AudioSystem.getClip();
		clip.open(sample);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
}
}