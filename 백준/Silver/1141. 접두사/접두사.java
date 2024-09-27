import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N;
    static String[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new String[N];
        for (int i = 0 ; i < N ; i++) arr[i] = bf.readLine();

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        System.out.println(solve());
    }

    static int solve() {
        Set<String> set = new HashSet<>();
        for (String s1 : arr) {
            if (set.isEmpty()) {
                set.add(s1);
                continue;
            }

            boolean possible = true;

            for (String s2 : set) {
                if (s2.startsWith(s1)) {
                    possible = false;
                    break;
                }
            }

            if (possible) set.add(s1);
        }

        return set.size();
    }
}