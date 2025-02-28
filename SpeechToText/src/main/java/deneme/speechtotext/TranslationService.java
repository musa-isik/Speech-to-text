/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deneme.speechtotext;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.JsonObject;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class TranslationService implements Runnable {

    private final String API_URL = "https://translation.googleapis.com/language/translate/v2?q=%s&target=%s&key=%s";

    private final String apiKey;
    private String lang;
    private String text;
    private HttpRequest request;
    private JsonParser jsonParser;
    private JTextArea jTextArea;
    
    public TranslationService(String apiKey, String text, String lang,JTextArea jTextArea) {
        this.apiKey = apiKey;
        this.text = text;
        this.lang = lang;
        this.jTextArea = jTextArea;
    }

    private HttpRequest createRequest(String api_url, String text, String lang) throws IOException {
        String url = String.format(api_url,text,lang,apiKey);
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        GenericUrl genericUrl = new GenericUrl(url);
        return requestFactory.buildGetRequest(genericUrl);
    }

    @Override
    public void run() {
        try {
            if (this.getText().length() > 0) {
                request = createRequest(API_URL, this.getText(), lang);
                HttpResponse response =request.execute();

                JsonObject jsonObject = jsonParser.parseString(response.parseAsString()).getAsJsonObject();
                String translatedText = jsonObject
                        .getAsJsonObject("data")
                        .getAsJsonArray("translations")
                        .get(0)
                        .getAsJsonObject()
                        .get("translatedText")
                        .getAsString();
                SwingUtilities.invokeLater(()-> jTextArea.append(translatedText+" \n"));
                System.out.println("çevirilen dil : "+lang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}
