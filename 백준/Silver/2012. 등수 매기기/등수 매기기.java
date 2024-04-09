import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];

        for (int i = 0 ; i < N ; i++) arr[i] = Integer.parseInt(bf.readLine());

        Arrays.sort(arr);

        long answer = 0;

        for (int i = 0 ; i < N ; i++)
            answer += Math.abs(arr[i] - (i+1));

        System.out.println(answer);
    }
}