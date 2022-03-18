import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class App {
    public static void main(String[] args) {
//        args = new String[]{"input.txt"};
        if(args.length == 0){
            System.out.println("Please provide a input file");
        }
        else if(args.length > 1){
            System.out.println("Please only provide one input file");
        }
        else{
            FileProcessor fileProcessor = new FileProcessor(args[0]);
            Theater movieTheater = new Theater();

            try{
                File file = new File(args[0]);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line = bufferedReader.readLine();

                while (line != null){
                    int output = movieTheater.reserve(line);
                    if(output == 1){
                        System.out.println("Invalid number of Seats requested with the order: " + line);
                    }
                    else if(output == -1) {
                        System.out.println("Sorry, cannot process request due to insufficient seats");
                    }

                    line = bufferedReader.readLine();
                }

                /* write output File */
                fileProcessor.writeOutputFile(movieTheater.getResult());

                /* Print Layout of the theater */
                movieTheater.printLayout();
                movieTheater.report();

                /* Test Theater.java */
                TheaterTester theaterTester = new TheaterTester();
                Theater theaterTest = new Theater();
                theaterTester.test(theaterTest);

            } catch (FileNotFoundException e) {
                System.err.println("Input file not Found");
                e.printStackTrace();
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
