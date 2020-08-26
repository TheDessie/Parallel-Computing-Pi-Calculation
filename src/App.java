import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        // set default parameter values
        int numTerms = 5000; // number of terms in the sequence
        int numThreads = 4;
        String outputFile = "ramanujanPi1.txt";
        boolean isQuiet = false;
        int granularity = 1;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equals("-n") || arg.equals("--terms")) {
                numTerms = Integer.parseInt(args[i + 1]);
                i++;
            } else if (arg.equals("-t") || arg.equals("--threads")) {
                numThreads = Integer.parseInt(args[i + 1]);
                i++;
            } else if (arg.equals("-o") || arg.equals("--output")) {
                outputFile = args[i + 1];
                i++;
            } else if (arg.equals("-g") || arg.equals("--granularity")) {
                granularity = Integer.parseInt(args[i + 1]);
                i++;
            } else if (arg.equals("-q") || arg.equals("--quiet")) {
                isQuiet = true;
            } else {
                System.err.println("Unknown option " + arg);
                System.exit(1);
            }
        }

        granularity = numTerms / numThreads;

        if (numTerms == 0) {
            System.err.println("Number ot terms cannot be 0!");
            System.exit(1);
        }

        if (numThreads == 0) {
            System.err.println("Number ot threads cannot be 0!");
            System.exit(1);
        }

        TaskManager taskMng = new TaskManager(numTerms, numThreads, isQuiet, granularity);

        long startTime, duration;

        if (!isQuiet) {
            System.out.println("Threads used in current run: " + numThreads);
        }

        startTime = System.currentTimeMillis();

        taskMng.proccess();

        duration = System.currentTimeMillis() - startTime;

        File file = new File(outputFile);
        try {
            file.createNewFile();
            FileWriter myWriter = new FileWriter(outputFile);
            myWriter.write(taskMng.getResult() + "");
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total execution time for current run (millis): " + duration);



    }
}
