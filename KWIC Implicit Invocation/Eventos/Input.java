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
                // Dividir la línea en oraciones basadas en signos de puntuación
                System.out.println("[InputReader] Leyendo línea: " + line.trim());
                String[] sentences = splitIntoSentences(line);
                System.out.println("[InputReader] Lineas despues del split: " + sentences.length);
                for (String sentence : sentences) {
                    System.out.println("[InputReader] Leyendo línea: " + sentence.trim());
                    eventManager.publish(new Event("line_read", sentence.trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método para dividir una línea en oraciones basadas en signos de puntuación.
    private String[] splitIntoSentences(String line) {
        return line.split("(?<=[.,!?])\\s+");
    }

    @Override
    public void onEvent(Event event) {
        // No necesita reaccionar a eventos
    }
}