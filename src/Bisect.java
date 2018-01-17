import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

public class Bisect {
    public static void main(String[] args) {
        List<Function<Double, Double>> bisect_fxs = new ArrayList<>(
                Arrays.asList(
                        x -> x - cos(x),
                        x -> (2 * log(x) - 1) / x,
                        x -> x - 0.2 * sin(x + 0.5)
                )
        );


        double start = 0.1;
        double end = 2.0;
        double e = 0.0001;

        bisect_fxs.forEach(func -> method_bisect(start, end, e, func));
    }

    private static void method_bisect(double x0, double x1, double e, Function<Double, Double> f) {

        double center = 0.0;
        double delta = abs(x0 - x1);
        int i = 0;

        while (delta > e && i < 100) {
            center = bisect(x0, x1);
            if (f.apply(x0) * f.apply(center) <= 0.0) {
                delta = abs(x1 - center);
                x1 = center;
            } else {
                delta = abs(x0 - center);
                x0 = center;
            }
            i++;
        }
        System.out.println("BISECT: " + center + " ITERATIONS: " + i);
    }

    private static double bisect(double x0, double x1) {
        double x2 = (x1 - x0) / 2;
        return x0 + x2;
    }

}
