/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deneme.speechtotext;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vosk.Model;
import org.vosk.Recognizer;

/**
 *
 * @author m-xz9
 */
public class VoskService2 implements Callable<Void> {

    private Model model = null;
    private MicrophoneService microphoneService;
    private BlockingQueue<String> resultQueue;

    public VoskService2(String model_path, MicrophoneService microphoneService, BlockingQueue<String> resultQueue) {
        this.microphoneService = microphoneService;
        this.resultQueue = resultQueue;

        try {
            model = new Model(model_path);
        } catch (IOException ex) {
            Logger.getLogger(VoskService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Void call() throws Exception {
        // Vosk tanıyıcıyı başlatma
        try {
            Recognizer recognizer = new Recognizer(model, 16000);
            byte[] buffer = new byte[4096];

            while (true) {
                int bytesRead = microphoneService.getMicrophone().read(buffer, 0, buffer.length);
                if (bytesRead > 0) {
                    if (recognizer.acceptWaveForm(buffer, bytesRead)) {
                        resultQueue.put(recognizer.getResult().split("\"text\"\\s*:\\s*")[1].split("\"")[1]);// sınıfı runnable yaparsak sonuç değişir mi araştır.
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(VoskService2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
