package componente2;

import java.util.ArrayList;
import java.util.List;

public class Rotacion {
    public List<String> process(List<String> lines) {
        List<String> rotations = new ArrayList<>();
        for (String line : lines) {
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
        }
        return rotations;
    }
}