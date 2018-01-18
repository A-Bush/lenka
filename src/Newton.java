import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

public class Newton {
    static List<Function<Double, Double>> fxs = new ArrayList<>(
            Arrays.asList(
                    x -> x * x - 20 * sin(x),
                    x -> x * x - cos(PI * x) * cos(PI * x),
                    x -> pow(E, x * (-1)) + x * x -2,
                    x -> x * log(x) + 0.125
            )
    );

    static List<Function<Double, Double>> dfxs = new ArrayList<>(
            Arrays.asList(
                    x -> 2 * x - 20 * cos(x),
                    x -> 2 * x + 2 * PI * sin(PI * x) * cos(PI * x),
                    x -> 2 * x - pow(E, x * (-1)),
                    x -> log(x) + 1
            )
    );

    public static void main(String[] args) {


        double start = 0.1;
        double e = 0.0001;

        for (int i = 0; i < fxs.size(); i++) {
            method_newton(start, e, fxs.get(i), dfxs.get(i));
        }
    }

    private static void method_newton(double x0, double e, Function<Double, Double> f, Function<Double, Double> df) {

        int j = 0;

        double x1 = newton(x0, f, df);
        while (abs(f.apply(x1)) > e) {
            x1 = newton(x1, f, df);
            j++;
        }
        System.out.println("NEWTON: " + x1 + " ITERATIONS: " + j );
    }

    private static double newton(double x, Function<Double, Double> f, Function<Double, Double> df) {
        return x - f.apply(x) / df.apply(x);
    }


}
