import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

public class Combine {
    static List<Function<Double, Double>> fxs = new ArrayList<>(
            Arrays.asList(
                    x -> x * x - cos(PI * x),
                    x -> 2 * log10(x) - x / 2 + 1,
                    x -> x + log10(x) + 0.5

            )
    );
    static List<Function<Double, Double>> dfxs = new ArrayList<>(
            Arrays.asList(
                    x -> 2 * x + PI * sin(PI * x),
                    x -> 2 / (x * log(10)) - 0.5,
                    x -> 1 + 1 / (x * log(10))
            )
    );
    static List<Function<Double, Double>> ddfxs = new ArrayList<>(
            Arrays.asList(
                    x -> PI * PI * cos(PI * x) + 2.0,
                    x -> (-2) / (x * x *log(10)),
                    x -> (-1) / (x * x * log(10))
            )
    );

    public static void main(String[] args) {

        double start = 0.1;
        double end = 1.0;
        double e = 0.0001;

        for (int i = 0; i < fxs.size(); i++){
            combine_method(start, end, e, fxs.get(i), dfxs.get(i), ddfxs.get(i));
        }

    }

    public static void combine_method(double x0, double x1, double e, Function<Double, Double> f, Function<Double, Double> df, Function<Double, Double> ddf ){
        if (df.apply(x0) * ddf.apply(x0) == 0){
            System.out.println("Cannot use combine method: f'(x) or f\"(x) equals 0");
            System.exit(0);
        }

        int i = 0;
        double result = 0;
        while (abs(x0 - x1) > 2 * e){
            if(f.apply(x0) * ddf.apply(x0) < 0){
                x0 = x0 - f.apply(x0) * (x0 -x1) / (f.apply(x0) - f.apply(x1));
            } else {
                x0 = x0 - f.apply(x0) / df.apply(x0);
            }
            if (f.apply(x1) * ddf.apply(x1) < 0){
                x1 = f.apply(x1) * (x1 - x0) / (f.apply(x1) - f.apply(x0));
            } else {
                x1 = x1 - f.apply(x1) / df.apply(x1);
            }
            i++;
        }

        result = (x0 + x1) / 2;
        System.out.println("COMBINE: " + result + " ITERATIONS: " + i );

    }


}
