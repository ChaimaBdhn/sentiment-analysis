package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    
    public static void readCSV(String filePath) {
        String line = "";
        String csvSeparator = ","; // Délimiteur CSV (peut être changé selon le format du fichier)

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                // Utilise le délimiteur pour diviser chaque ligne en colonnes
                String[] columns = line.split(csvSeparator);

                // Afficher chaque colonne
                for (String column : columns) {
                    System.out.print(column + "\t");
                }
                System.out.println(); 
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            String csvFilePath = args[0];
            readCSV(csvFilePath);
        } else {
            System.out.println("Veuillez fournir le chemin du fichier CSV en argument.");
        }
    }
}
