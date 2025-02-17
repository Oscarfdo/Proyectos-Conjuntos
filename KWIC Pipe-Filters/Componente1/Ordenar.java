

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ordenar implements Runnable {
    private PipedInputStream entrada;
    private PipedOutputStream salida;

    public Ordenar(PipedInputStream entrada, PipedOutputStream salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(salida));

            List<String> rotaciones = new ArrayList<>();
            String line;
            
            // Leer todas las líneas del tubo
            while ((line = reader.readLine()) != null) {
                rotaciones.add(line);
            }

            // Ordenar las rotaciones alfabéticamente
            Collections.sort(rotaciones, String.CASE_INSENSITIVE_ORDER);

            // Escribir las rotaciones ordenadas en el siguiente tubo
            for (String rotacion : rotaciones) {
                writer.write(rotacion + "\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
