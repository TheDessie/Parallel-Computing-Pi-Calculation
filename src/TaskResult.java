import org.apfloat.Apfloat;

public class TaskResult {

    private Apfloat sum;
    private int numTerms;
    private Apfloat incrementalMultiplier;

    public TaskResult(Apfloat sum, int numTerms, Apfloat incrementalMultiplier) {
        this.sum = sum;
        this.numTerms = numTerms;
        this.incrementalMultiplier = incrementalMultiplier;
    }

    public Apfloat getSum() {
        return sum;
    }

    public int getNumTerms() {
        return numTerms;
    }

    public Apfloat getIncrementalMultiplier() {
        return incrementalMultiplier;
    }
}
