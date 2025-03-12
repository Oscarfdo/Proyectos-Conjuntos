/*
 Sofia Alejandra Vargas Flores & Oscar Fernando Hernandez Lopez
 12/Marzo/2025
 Clase Main
 Controla el flujo de ejecución del programa
 */
package Eventos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Pedir al usuario el nombre del archivo de entrada (sin extensión)
        System.out.print("Ingrese el nombre del archivo (sin extensión): ");
        String fileName = scanner.nextLine().trim();
        
        // Construcción de nombres de archivos
        String inputFilePath = fileName + ".txt";
        String outputFilePath = fileName + "Output.txt";
        
        MasterControl masterControl = new MasterControl();
        
        // Pasar los nombres de archivo correctamente
        Input inputReader = new Input(masterControl, inputFilePath);
        CircularShift shifter = new CircularShift(masterControl);
        Alphabetizer alphabetizer = new Alphabetizer(masterControl);
        Output outputWriter = new Output(masterControl, outputFilePath);

        // Ejecutar flujo
        inputReader.readInputFromFile();
        alphabetizer.publishSortedShifts();
    }
}
