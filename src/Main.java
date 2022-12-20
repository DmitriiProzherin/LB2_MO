public class Main {
    public static void main(String[] args) {

        HalfDivisionSearch search = new HalfDivisionSearch();

        double min = search.naive(-11, 1, 0.01);

        System.out.printf("Минимальное значение функции: %.12f", min);
    }
}