


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdenAlfabetico implements Runnable {
    private final Pipe inputQueue;
    private final Pipe outputQueue;

    public OrdenAlfabetico(Pipe inputQueue, Pipe outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
   public void run() {
    List<String> block = new ArrayList<>();
    final int BLOCK_SIZE = 1000; // o el tamaño que consideres adecuado
    try {
        while (true) {
            String line = inputQueue.take();
            if (line.equals("EOF")) {
                if (!block.isEmpty()) {
                    processBlock(block);
                }
                outputQueue.put("EOF");
                break;
            }
            block.add(line);
            if (block.size() >= BLOCK_SIZE) {
                processBlock(block);
                block.clear();
            }
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

private void processBlock(List<String> block) throws InterruptedException {
    Collections.sort(block, String.CASE_INSENSITIVE_ORDER);
    for (String sortedLine : block) {
        outputQueue.put(sortedLine);
    }
    System.out.println("[OrdenAlfabetico] Bloque procesado y enviado.");
}

}