import org.apfloat.Apfloat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskManager {

    private int numTerms;
    private int numThreads;
    private Apfloat result = Apfloat.ZERO;
    private boolean isQuiet;
    private int granularity;

    public TaskManager(int numTerms, int numThreads, boolean isQuiet, int granularity) {
        this.numTerms = numTerms;
        this.numThreads = numThreads;
        this.isQuiet = isQuiet;
        this.granularity = granularity;
    }

    public Apfloat getResult() {
        return result;
    }

    public void proccess() {

        RamanujanPi1 ramanujanPi1 = new RamanujanPi1(numTerms);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

//        int numTermsByThread = numTerms / numThreads;
        int numTermsByThread = granularity;
        int currentIndex = 0;

        List<Future<TaskResult>> results = new ArrayList<Future<TaskResult>>();

        for (int i = 0; i < numTerms; i+=numTermsByThread) {

            if (i + 2 * numTermsByThread > numTerms) { // проверка дали не е последното i
                numTermsByThread = numTerms - i;
            }

            Task task = new Task(i, ramanujanPi1, numTermsByThread, currentIndex, isQuiet);
            Future<TaskResult> result = executor.submit(task);

            results.add(result);

            currentIndex += numTermsByThread;

        }

        Apfloat total = Apfloat.ZERO;
        Apfloat nextMultiplier;
        Apfloat term;

        nextMultiplier  = Apfloat.ONE;

        // засичане на времето на Thread master

        long startTime, duration;
        startTime = System.currentTimeMillis();

        if (!isQuiet) {
            System.out.println("Thread master started.");
        }
        // засичане на времето на Thread master

        for (int i = 0; i < results.size(); i++) {
            try {
                TaskResult taskResult = results.get(i).get();
                term = taskResult.getSum().multiply(nextMultiplier);
                total = total.add(term);

                nextMultiplier = taskResult.getIncrementalMultiplier();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // засичане на времето на Thread master
        duration = System.currentTimeMillis() - startTime;

        if (!isQuiet) {
            System.out.println("Thread master stopped, duration was (millis): " + duration);
        }
        // засичане на времето на Thread master

        total = ramanujanPi1.calcResult(total);
        result = total;

        executor.shutdown();

    }

}
