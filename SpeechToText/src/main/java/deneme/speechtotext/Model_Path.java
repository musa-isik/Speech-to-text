/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deneme.speechtotext;

import javax.swing.text.JTextComponent;

/**
 *
 * @author m-xz9
 */
public class Model_Path{
    private final String models_path="C:\\Users\\m-xz9\\Documents\\NetBeansProjects\\SpeechToText\\src\\main\\java\\";
    private String selected_language;
    private JTextComponent component;
    private final String[][] model={
        {"Türkçe","vosk-model-small-tr-0.3"},
        {"İngilizce","models\\vosk-model-en-us-0.22"},
        {"Almanca","models\\vosk-model-de-0.21"},
        {"Fransızca","models\\vosk-model-fr-0.6-linto-2.2.0"}};
    public Model_Path(String selected_language){
        this.selected_language= selected_language;
    }
     
    public String getModelPath() {
        for (String[] entry : model) {
            if (entry[0].equalsIgnoreCase(selected_language)) {
                return models_path + entry[1];
            }
        }
        return null;
    }
}
