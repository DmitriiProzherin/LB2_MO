public class HalfDivisionSearch {

    private final double eps = 1e-20;
    private int func_call_amount = 0;
    private int step_number = 0;
    private double f_min;

    private double f(double x){
        func_call_amount++;
        return Math.pow(x, 2);
    }


    public double naive(double a, double b, double eps){
        assert b>a;
        assert eps>0;

        double length = b - a;
        double left, mid, right;
        double f_left, f_mid, f_right;

        System.out.printf("%5s%10s%10s%10s%10s%10s%10s%10s%10s%10s\n",
                "step", "length", "a", "b", "left", "mid", "right", "f_left", "f_mid", "f_right");

        if (length > 2 * eps) {
            mid = a + length / 2;
            left = mid - length / 4;
            right = mid + length / 4;

            f_left = f(left);
            f_mid = f(mid);
            f_right = f(right);

            System.out.printf("%5d%10f%10f%10f%10f%10f%10f%10f%10f%10f\n",
                    step_number, length, a, b, left, mid, right, f_left, f_mid, f_right);

            while (length > 2 * eps) {
                step_number++;
                length = length / 2;

                if (f_left < f_mid)
                    {

                        b = mid;
                        mid = left;
                        left = mid - length / 4;
                        right = mid + length / 4;

                        f_mid = f_left;
                        f_left = f(left);
                        f_right = f(right);

                        f_min = min(f_left, f_right, f_mid);

                    }
                else if (f_mid < f_right)
                    {
                        f_min = f_mid;
                        a = left;
                        b = right;
                        left = mid - length / 4;
                        right = mid + length / 4;

                        f_left = f(left);
                        f_right = f(right);

                        f_min = min(f_left, f_right, f_mid);
                    }
                else if (f_right < f_mid)
                    {
                        f_min = f_right;
                        a = mid;
                        mid = right;
                        left = mid - length / 4;
                        right = mid + length / 4;

                        f_mid = f_right;
                        f_left = f(left);
                        f_right = f(right);

                        f_min = min(f_left, f_right, f_mid);
                    }

                System.out.printf("%5d%10f%10f%10f%10f%10f%10f%10f%10f%10f\n",
                        step_number, length, a, b, left, mid, right, f_left, f_mid, f_right);

            }

            System.out.println("Количество итераций: " + ++step_number);
            System.out.println("Количество вызовов функции: " + func_call_amount);
        }

        else {
            f_min = f((b - a) / 2);
        }

        System.out.printf("Минимальное значение функции: %10f", f_min);

        return f_min;
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
