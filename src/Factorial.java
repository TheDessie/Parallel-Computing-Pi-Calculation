import org.apfloat.Apfloat;

public class Factorial {

    public static Apfloat factorialRange(int from, int to) {
        if (from == to) {
            return new Apfloat(to);
        }

        int middle = (from + to) / 2;
        Apfloat start = factorialRange(from, middle);
        Apfloat end = factorialRange(middle + 1, to);

        return start.multiply(end);
    }

}
