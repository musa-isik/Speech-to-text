/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deneme.speechtotext;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author m-xz9
 */
public class MicrophoneService {

    private final AudioFormat format;
    private final DataLine.Info info;
    private TargetDataLine microphone;

    public MicrophoneService() {
        format = new AudioFormat(16000, 16, 1, true, false);
        info = new DataLine.Info(TargetDataLine.class, format);
    }

    public void startMicrophone() {
        try {
            microphone = (TargetDataLine) AudioSystem.getLine(getInfo());
            getMicrophone().open(getFormat());
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MicrophoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        getMicrophone().start();
        System.out.println("Dinlemeye başlandı...");

    }

    public AudioFormat getFormat() {
        return format;
    }

    public DataLine.Info getInfo() {
        return info;
    }

    public TargetDataLine getMicrophone() {
        return microphone;
    }
}
