public class Main {
    public static void main(String[] args) {

        HalfDivisionSearch search = new HalfDivisionSearch();

        double min = search.naive(2, 10, 0.001);

        System.out.printf("Минимальное значение функции: %.12f", min);
    }
}