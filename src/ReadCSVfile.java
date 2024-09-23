import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader; 



public class ReadCSVfile {

    /* Reading a CSV file and print out the content  */
    public static void readFile(String filePath) {

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while( (nextLine = reader.readNext()) != null) {
                for(String line : nextLine) {
                    System.out.println(line + " ");
                }
                System.out.println();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }




    public static void main(String[] args) {
        String csvFile = "./data/testdata.manual.2009.06.14.csv"; // mettre dans le main ?

        readFile(csvFile);
    }

}


 
