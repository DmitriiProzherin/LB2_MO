public class HalfDivisionSearch {

    private int func_call_amount = 0;
    private int step_number = 0;
    private double f_min;

    private double f(double x){
        func_call_amount++;
        return Math.pow(x, 2);
    }


    public double naive_eps(double a, double b, double eps){
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

    public double naive_length(double a, double b, double delta_length){

        double length = b - a;
        double left, mid, right;
        double f_left, f_mid, f_right;

        System.out.printf("%5s%10s%10s%10s%10s%10s%10s%10s%10s%10s\n",
                "step", "length", "a", "b", "left", "mid", "right", "f_left", "f_mid", "f_right");

        if (length != delta_length) {
            mid = a + length / 2;
            left = mid - length / 4;
            right = mid + length / 4;

            f_left = f(left);
            f_mid = f(mid);
            f_right = f(right);

            System.out.printf("%5d%10f%10f%10f%10f%10f%10f%10f%10f%10f\n",
                    step_number, length, a, b, left, mid, right, f_left, f_mid, f_right);

            while (length != delta_length) {
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

    private double min(double b7, double b8, double b9) {
        return Math.min(Math.min(b7, b8), b9);
    }

}
