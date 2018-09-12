import static java.lang.Math.*;

import java.util.function.Function;

public class ABushTest {

    public static void main(String[] args) {
        int frame_start = 0;
        int frame_end = 50;

//        https://www.desmos.com/calculator


//        double x = 1.0;
//        double y = (1 / (0.39894228 * sqrt(2*PI)) * pow(E, (-pow(x, 2) / (2 * 0.39894228 * 0.39894228))));

        final double sigma = 0.39894228d;
        final double mat = 1.0d;

        final Function<Double, Double> gauss = x -> (1 / (sigma * sqrt(2 * PI)) * pow(E, (-pow((x - mat), 2) / (2 * sigma * sigma))));

        int ranges = 5;
        final double gauss_start = 0.0d;
        final double gauss_end = 2.0d;


        double[] gauss_ranges = new double[ranges];
        double delimiter = (gauss_end - gauss_start) / ranges;

        for (int i = 0; i < ranges; i++) {
            gauss_ranges[i] = delimiter * (i + 1) - delimiter / 2;
        }

        int frame_range = (frame_end - frame_start) / ranges;
        int[][] frame_ranges = new int[ranges][frame_range];

        for (int i = 0; i < ranges; i++) {
            for (int j = 0; j < frame_range; j++) {
                frame_ranges[i][j] = i * 10 + j;
            }
        }


        int[] how_much_frames_show = new int[ranges];

        for (int i = 0; i < ranges; i++) {
            how_much_frames_show[i] = new Double(gauss.apply(gauss_ranges[i]) * 10).intValue();
        }


//        int[] masks = {
//                0, 32, 68, 146, 340, 682, 698, 954, 955, 1019, 1023
//        };

        int [][] masks = {
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,0},
                {0,0,0,1,0,0,0,1,0,0},
                {0,0,1,0,0,1,0,0,1,0},
                {0,1,0,1,0,1,0,1,0,0},
                {1,0,1,0,1,0,1,0,1,0},
                {1,1,1,0,1,0,1,0,1,0},
                {1,1,1,0,1,1,1,0,1,0},
                {1,1,1,0,1,1,1,0,1,1},
                {1,1,1,1,1,1,1,0,1,1},
                {1,1,1,1,1,1,1,1,1,1}
        };


        print_frames(frame_ranges, masks, how_much_frames_show);





//        System.out.println(gauss.apply(1.0d));

//        for (double v : gauss_ranges) {
//            System.out.print(v + " ");
//        }
//        for (double v : gauss_ranges) {
//            System.out.println(v + " " + gauss.apply(v));
//        }
//
//        for (int v : how_much_frames_show) {
//            System.out.println(v);
//        }

        System.out.println();
        System.out.println(" All The frames;");
        for (int i = 0; i < ranges; i++) {
            for (int j = 0; j < frame_range; j++){
                System.out.print(frame_ranges[i][j] + " ");
            }
            System.out.println();
        }

    }


    public static void print_frames(int[][] frames, int[][] mask, int[] howMuchFramesShow){

        System.out.println("Frames to print:");
        for (int line = 0; line < frames.length; line++){
            int framesToShow = howMuchFramesShow[line];
            int[] framesToShowMask = mask[framesToShow];

            for (int f = 0; f < frames[line].length; f++){
                if (framesToShowMask[f] != 0 ){
                    System.out.print(frames[line][f] + ", ");
                }
            }
        }


    }
}
