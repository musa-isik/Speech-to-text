/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deneme.speechtotext;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import javax.swing.JTextArea;

/**
 *
 * @author m-xz9
 */
public class WriteTranslated extends Thread{
    private final String API_KEY="";
    private final String language;
    private final HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
    private JTextArea textArea;
    private String text;
    private String url;
            
    public WriteTranslated(String language,JTextArea textArea,String text){
    this.language = language;
    this.textArea = textArea;
    this.text =text;
    this.url=createUrl();
    
    
    }
    
    private String createUrl(){
    String url = String.format(
                "https://translation.googleapis.com/language/translate/v2?q=%s&target=%s&key=%s",
                text, // Çevrilecek metin
                language, // Hedef dil (Türkçe)
                API_KEY // API anahtarı
        );
    return url;
    }
    
    
    
    @Override
    public void run(){
    
    }
    
    
//    String url = String.format(
//                "https://translation.googleapis.com/language/translate/v2?q=%s&target=%s&key=%s",
//                text, // Çevrilecek metin
//                language, // Hedef dil (Türkçe)
//                API_KEY // API anahtarı
//        );
//        
//        GenericUrl genericUrl = new GenericUrl(url);
//        HttpRequest request = requestFactory.buildGetRequest(genericUrl);
//        HttpResponse response = request.execute();
//        String responseBody = response.parseAsString();
//        
//    
}
