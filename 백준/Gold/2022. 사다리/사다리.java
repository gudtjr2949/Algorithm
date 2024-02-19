import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static double X, Y, C;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        X = Double.parseDouble(st.nextToken());
        Y = Double.parseDouble(st.nextToken());
        C = Double.parseDouble(st.nextToken());

        binarySearch();
    }

    static void binarySearch() {
        double left = 0;
        double right = Math.min(X, Y);

        while (true) {
            double mid = (left + right) / 2; // W

            double result = (Math.sqrt(Y*Y - mid*mid) * Math.sqrt(X*X - mid*mid)) / (Math.sqrt(Y*Y - mid*mid) + Math.sqrt(X*X - mid*mid));

            if (Math.abs(result - C) <= 0.001) {
                System.out.println(mid);
                break;
            }

            if (result > C) left = mid;
            else right = mid;
        }
    }
}