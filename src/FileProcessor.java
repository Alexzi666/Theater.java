import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class FileProcessor {
    String filename;

    public FileProcessor(String filename){
        this.filename = filename;
    }

    public String readInputFile(String filename){
        String fileContent = null;
        try{
            File inputFile = new File(filename);
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();

            while(line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            fileContent = sb.toString();

        } catch (FileNotFoundException e) {
            System.err.println("Input File Not Found.");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }


    public void writeOutputFile(Map<String, List<String>> map){
        BufferedWriter writer;
//        String path = "./Walmart-MovieTheater/";
        try {
            writer = new BufferedWriter(new FileWriter("Seating_Assignment_Report.txt"));
            Iterator<Entry<String, List<String>>> iterator =  map.entrySet().iterator();

            while(iterator.hasNext()) {
                Entry<String, List<String>> entry = iterator.next();
                String str = entry.getKey() + " " + entry.getValue();
                writer.write(str + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        System.out.println("Seating_Assignment_Report.txt file is located at: ./Seating_Assignment_Report.txt");
    }
}












