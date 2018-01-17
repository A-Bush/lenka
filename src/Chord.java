import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

public class Chord {

    public static void main(String[] args) {

        List<Function<Double, Double>> chord_fxs = new ArrayList<>(
                Arrays.asList(
                        x -> 2 * sqrt(x) - cos(x),
                        x -> (x - 1) * (x - 1) + pow(E, x * (-1)),
                        x -> 2 - x - log(x)
                )
        );

        double start = 0.5;
        double end = 2.0;
        double e = 0.0001;


        chord_fxs.forEach(func -> method_chord(start, end, e, func));
        System.out.println("DONE");
    }


    private static void method_chord(double x0, double x1, double e, Function<Double, Double> f) {

        double center = 0.0;
        double delta = abs(x0 - x1);
        int i = 0;

        while (delta > e && i < 100) {
            center = chord(x0, x1, f);
            if (f.apply(x0) * f.apply(center) <= 0.0) {
                delta = abs(x1 - center);
                x1 = center;
            } else {
                delta = abs(x0 - center);
                x0 = center;
            }
            i++;
        }
        System.out.println("Chords: " + center + " iters " + i);
    }

    private static double chord(double x0, double x1, Function<Double, Double> f) {
        double up = (f.apply(x0) * (x1 - x0));
        double down = f.apply(x1) - f.apply(x0);

        return x0 - up / down;
    }

}