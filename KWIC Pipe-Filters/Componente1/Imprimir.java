
import java.io.*;

public class Imprimir implements Runnable {
    private PipedInputStream entrada;

    public Imprimir(PipedInputStream entrada) {
        this.entrada = entrada;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));
            String line;
            System.out.println("Rotaciones ordenadas:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
