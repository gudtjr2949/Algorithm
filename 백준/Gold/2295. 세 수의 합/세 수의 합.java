import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N, answer;
    static int[] arr;
    static Set<Integer> set;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr);

        for(int i = 0 ; i < N ; i++){
            for(int j = i ; j < N ; j++){
                set.add(arr[i] + arr[j]);
            }
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                int tmp = arr[i] - arr[j];

                if (set.contains(tmp)) {
                    answer = Math.max(answer, arr[i]);
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
    }

    static void init() {
        arr = new int[N];
        set = new HashSet<>();
    }
}