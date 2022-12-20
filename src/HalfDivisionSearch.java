import java.util.ArrayList;
import java.util.Arrays;

public class HalfDivisionSearch {

    private final double eps = 1e-15;
    private final ArrayList<double[]> outTable = new ArrayList<>();

    private double f(double x){
        return Math.sin(x)*Math.pow(x, 3);
    }


    public double naive(double a, double b, double eps){
        assert b>a;
        assert  eps>0;

        double length = b - a;
        double mid = a + length / 2.0;
        double left = a, right = b;

        outTable.add(new double[] {length, a, b, left, mid, right, f(left), f(mid), f(right)});

        while (length > eps) {
            mid = left + length / 2.0;
            left = mid - length / 4.0;
            right = mid + length / 4.0;

            length = right - left;
            mid = min_func_val(left, mid, right);

            outTable.add(new double[] {length, a, b, left, mid, right, f(left), f(mid), f(right)});
        }

        System.out.printf("\nМетод половинного деления.\nk%13s%15s%16s%17s","delta", "a_k", "b_k","x_1/4");
        System.out.printf("%16s%16s%17s%16s%16s\n"," x_1/2", "x_3/4", "f(x_1/4)","f(x_1/2)", "f(x_3/4)");
        outTable.forEach(l -> {
            System.out.print(outTable.indexOf(l));
            for (double v : l) {System.out.printf("\t%13f", v);}
            System.out.println();

        });

        return mid;
    }

    private double min_func_val(double a, double b, double c){
        return Math.min(Math.min(f(a), f(b)), f(c));
    }

}
