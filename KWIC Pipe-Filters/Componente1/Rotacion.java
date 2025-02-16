import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Rotacion implements Runnable {
    private PipedInputStream entrada;
    private PipedOutputStream salida;

    public Rotacion(PipedInputStream entrada, PipedOutputStream salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(salida));

            String line;
            while ((line = reader.readLine()) != null) {
                List<String> rotations = generarRotaciones(line);
                for (String rotation : rotations) {
                    writer.write(rotation + "\n");
                }
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> generarRotaciones(String line) {
        List<String> rotations = new ArrayList<>();
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            StringBuilder rotation = new StringBuilder();
            for (int j = i; j < words.length; j++) {
                rotation.append(words[j]).append(" ");
            }
            for (int j = 0; j < i; j++) {
                rotation.append(words[j]).append(" ");
            }
            rotations.add(rotation.toString().trim());
        }
        return rotations;
    }
}
