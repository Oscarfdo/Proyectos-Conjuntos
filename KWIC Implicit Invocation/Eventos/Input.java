package Eventos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input implements EventListener {
    private MasterControl eventManager;
    private String filePath;

    public Input(MasterControl eventManager, String filePath) {
        this.eventManager = eventManager;
        this.filePath = filePath;
    }

    public void readInputFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("[InputReader] Leyendo línea: " + line.trim());
                String[] sentences = splitIntoSentences(line);
                System.out.println("[InputReader] Líneas después del split: " + sentences.length);
                for (String sentence : sentences) {
                    System.out.println("[InputReader] Procesando oración: " + sentence.trim());
                    eventManager.publish(new Event("line_read", sentence.trim()));
                }
            }
            System.out.println("[InputReader] Fin de la lectura del archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] splitIntoSentences(String line) {
        return line.split("(?<=[.,!?])\\s+");
    }

    @Override
    public void onEvent(Event event) {
        // No necesita reaccionar a eventos
    }
}