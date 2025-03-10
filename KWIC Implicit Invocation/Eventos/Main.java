package Eventos;

public class Main {
    public static void main(String[] args) {
        MasterControl masterControl = new MasterControl();

        Input inputReader = new Input(masterControl, "/Users/kueven/Documents/Proyectos-Conjuntos/Proyectos-Conjuntos/KWIC Implicit Invocation/Eventos/Leer.txt");
        CircularShift shifter = new CircularShift(masterControl);
        Alphabetizer alphabetizer = new Alphabetizer(masterControl);
        Output outputWriter = new Output(masterControl);

        inputReader.readInputFromFile();  
        alphabetizer.publishSortedShifts();  
        
    }
}