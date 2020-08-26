import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class RamanujanPi1 {

    private final int precisionByTerm = 6;
    private Apfloat a, b, c2, c4, one, three, four, eight,sixtyfour;

    public RamanujanPi1(int numTerms) {

        int precision = numTerms * precisionByTerm;

        a = new Apfloat(1103, precision);
        b = new Apfloat(26390, precision);
        c2 = new Apfloat(9801); // ApfloatMath.pow(c, 2);
        c4 = new Apfloat(96059601); //ApfloatMath.pow(c2, 2);
        sixtyfour = new Apfloat(64); // 4 ^ 3
        one = new Apfloat(1);
        three = new Apfloat(3);
        four = new Apfloat(4, precision);
        eight = new Apfloat(8, precision);

    }

    public Apfloat calcMultiplier(Apfloat prevMultiplier, int index) {
        Apfloat ind = new Apfloat(index);
        Apfloat m3 = four.multiply(ind).subtract(three);
        Apfloat m2 = m3.add(one);
        Apfloat m1 = m2.add(one);
        Apfloat multiplier = prevMultiplier
                .multiply(m3).multiply(m2).multiply(m1)
                .divide(ApfloatMath.pow(new Apfloat(index), 3)
                .multiply(c4).multiply(sixtyfour));

        return multiplier;
    }

    public Apfloat calcTerm(Apfloat multiplier, int index) {
        return multiplier.multiply(a.add(b.multiply(new Apfloat(index))));
    }

    public Apfloat calcResult(Apfloat sum) {
        return Apfloat.ONE.divide(sum.multiply(ApfloatMath.sqrt(eight)).divide(c2));
    }
}
