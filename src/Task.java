import org.apfloat.Apfloat;

import java.util.concurrent.Callable;

public class Task implements Callable {

    private int taskIndex;
    private RamanujanPi1 ramanujanPi1;
    private int numTermsByThread;
    private int startIndex;
    private boolean isQuiet;

    public Task(int taskIndex, RamanujanPi1 ramanujanPi1, int numTermsByThread, int startIndex, boolean isQuiet) {
        this.taskIndex = taskIndex;
        this.ramanujanPi1 = ramanujanPi1;
        this.numTermsByThread = numTermsByThread;
        this.startIndex = startIndex;
        this.isQuiet = isQuiet;
    }

    @Override
    public TaskResult call() throws Exception {

        long startTime, duration;
        startTime = System.currentTimeMillis();

//        if (!isQuiet) {
//            System.out.println("Thread " + taskIndex + " started.");
//        }

        Apfloat sum = Apfloat.ZERO;
        Apfloat incrementalMultiplier = Apfloat.ONE;

        for (int i = startIndex; i < startIndex + numTermsByThread; i++) {
            sum = sum.add(ramanujanPi1.calcTerm(incrementalMultiplier, i));
            incrementalMultiplier = ramanujanPi1.calcMultiplier(incrementalMultiplier, i + 1);
        }

        duration = System.currentTimeMillis() - startTime;

//        if (!isQuiet) {
//            System.out.println("Thread " + taskIndex + " stopped, duration was (millis): " + duration);
//        }

        return new TaskResult(sum, numTermsByThread, incrementalMultiplier);
    }


}
