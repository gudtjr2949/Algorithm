import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] originX, originY, arrX, arrY;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());

        arrX = new int[n];
        arrY = new int[n];
        originX = new int[n];
        originY = new int[n];

        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arrX[i] = x;
            arrY[i] = y;
            originX[i] = x;
            originY[i] = y;
        }

        System.out.println(solve());
    }

    static long solve() {
        Arrays.sort(arrX);
        Arrays.sort(arrY);

        int rX = arrX[n/2];
        int rY = arrY[n/2];

        long sum = 0;

        for (int i = 0 ; i < n ; i++) {
            sum += Math.abs(rX - originX[i]) + Math.abs(rY - originY[i]);
        }

        return sum;
    }
}