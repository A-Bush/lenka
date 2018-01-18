import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

public class Relax {

    static List<Function<Double, Double>> fxs = new ArrayList<>(
            Arrays.asList(
                    x -> 2 - x * pow(E, x),
                    x -> pow(2, x) - 4 * x

            )
    );

    static List<Function<Double, Double>> dfxs = new ArrayList<>(
            Arrays.asList(
                    x -> (-1) * x * pow(E, x) - pow(E, x),
                    x -> pow(2, x) * log(2) - 4
            )
    );

    public static void main(String[] args) {
        double start = 0.1;
        double end = 1.0;
        double e = 0.0001;

        for (int i = 0; i < fxs.size(); i++) {
            relax_method(start, end, e, fxs.get(i), dfxs.get(i));
        }
    }

    public static void relax_method(double x0, double x1, double e, Function<Double, Double> f, Function<Double, Double> df) {

        double M = df.apply(x0), m = df.apply(x0), val;
        for (double c = x0; c <= x1; c += e) {
            val = df.apply(c);
            M = M > val ? M : val;
            m = m < val ? m : val;
        }

        if (M * m < 0) {
            System.out.println("cannot use relax method - f'(x) sign changed on [ " + x0 + " , " + x1 + " ]");
            return;
        }
        double q = 1 - m / M;
        double d = e * (1 - q) / q;
        double start = (x0 + x1) / 2;
        double result = x0;

        int i = 0;
        while (abs(result - start) > d) {
            start = result;
            result = start - f.apply(start) / M;
            i++;
        }
        System.out.println("REALAX: " + result + " ITERATIONS: " + i);

    }
}
