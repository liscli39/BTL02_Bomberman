/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author Liscli
 */
public class Sound {
    public static void playSound(String soundName)
    {
        try 
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
            
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex)
        {
//            System.out.println("Error with playing sound.");
//            System.out.print(ex.getMessage());
        }
    }
    public static boolean isRunning(){
        return clip.isRunning();
    }
    public static void start(){
        clip.start();
    }
    public static void stop(){
        clip.stop();
    }
    private static Clip clip;
}
