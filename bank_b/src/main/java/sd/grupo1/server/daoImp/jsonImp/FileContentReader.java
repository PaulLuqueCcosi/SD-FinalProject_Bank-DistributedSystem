package sd.grupo1.server.daoImp.jsonImp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileContentReader {
    public synchronized static String readFromFile(String directory, String filee) {

        String filePath = directory + filee;

        StringBuilder content = new StringBuilder();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
                content.append(System.lineSeparator()); // Agregar salto de línea
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            
            // Puedes manejar el error según tus necesidades.
            // Por ejemplo, puedes retornar un mensaje de error específico.
            return "Error: Archivo no encontrado.";
        }

        return content.toString();
    }

    public synchronized static void saveToFile(String directory, String fileName, String content) {
        try {
            File file = new File(directory, fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            System.out.println("Archivo guardado exitosamente en: " + file.getAbsolutePath());
        } catch (IOException e) {
            // Puedes manejar el error según tus necesidades.
            // Por ejemplo, puedes imprimir un mensaje de error específico.
            System.err.println("Error al guardar el archivo.");
        }
    }

}