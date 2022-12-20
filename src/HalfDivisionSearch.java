import java.util.ArrayList;
import java.util.Arrays;

public class HalfDivisionSearch {

    private final double eps = 1e-20;
    private final ArrayList<String[]> outTable = new ArrayList<>();

    private double f(double x){
        return Math.sin(x)*Math.pow(x, 3);
    }


    public double naive(double a, double b, double eps){
        assert b>a;
        assert  eps>0;

        double length = Math.abs(b - a);
        double mid = a + length / 2.0;
        double left = a, right = b;

        outTable.add(wr(length, a, b, left, mid, right, f(left), f(mid), f(right)));

        while (length > eps) {
            mid = left + length / 2.0;
            left = mid - length / 4.0;
            right = mid + length / 4.0;

            length = Math.abs(right - left);
            mid = min_func_val(left, mid, right);

            outTable.add(wr(length, a, b, left, mid, right, f(left), f(mid), f(right)));
        }

        System.out.printf("\nМетод половинного деления.\nk%13s%15s%16s%17s","delta", "a_k", "b_k","x_1/4");
        System.out.printf("%16s%16s%17s%16s%16s\n"," x_1/2", "x_3/4", "f(x_1/4)","f(x_1/2)", "f(x_3/4)");
        outTable.forEach(l -> {
            System.out.print(outTable.indexOf(l)+"\t");
            for (String s : l) { System.out.print("\t\t" + s); }
            System.out.println();
        });

        return f(mid);
    }

    private double min_func_val(double a, double b, double c){
        double min_f = Math.min(Math.min(f(a), f(b)), f(c));
        if (Math.abs(min_f - f(a)) < eps) return a;
        if (Math.abs(min_f - f(b)) < eps) return b;
        if (Math.abs(min_f - f(c)) < eps) return c;
        return 0;
    }

    private String[] wr(double b1, double b2, double b3, double b4, double b5, double b6, double b7, double b8, double b9){
        String[] res = new String[] {
                            String.format("%10f", b1),
                            String.format("%10f", b2),
                            String.format("%10f", b3),
                            String.format("%10f", b4),
                            String.format("%10f", b5),
                            String.format("%10f", b6),
                            String.format("%10f", b7),
                            String.format("%10f", b8),
                            String.format("%10f", b9),
                    };

        double min_val = min(b4, b5, b6);
        double min_func = min(b7, b8, b9);

        if (Math.abs(min_val - b4) < eps) res[3] = "\u001B[35m" + res[3] + "\u001B[0m";
        else if (Math.abs(min_val - b5) < eps) res[4] = "\u001B[35m" + res[4] + "\u001B[0m";
        else if (Math.abs(min_val - b6) < eps) res[5] = "\u001B[35m" + res[5] + "\u001B[0m";

        if (Math.abs(min_func - b7) < eps) res[6] = "\u001B[35m" + res[6] + "\u001B[0m";
        else if (Math.abs(min_func - b8) < eps) res[7] = "\u001B[35m" + res[7] + "\u001B[0m";
        else if (Math.abs(min_func - b9) < eps) res[8] = "\u001B[35m" + res[8] + "\u001B[0m";

        return res;
    }

    private double min(double b7, double b8, double b9) {
        return Math.min(Math.min(b7, b8), b9);
    }

}
