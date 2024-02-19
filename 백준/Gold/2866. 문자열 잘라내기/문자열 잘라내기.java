import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];

        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C ; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        binarySearch();
    }

    static void binarySearch() {
        int left = 0;
        int right = C-1;

        while (left <= right) {
            int mid = (left + right) / 2; // 지울 행

            if (solve(mid)) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        System.out.println(left);
    }

    static boolean solve(int mid) {
        Set<String> set = new HashSet<>();

        for (int i = 0 ; i < C ; i++) {
            String s = "";
            for (int j = mid+1 ; j < R ; j++) {
                s += arr[j][i];
            }
            if (set.contains(s)) return false;
            set.add(s);
        }

        return true;
    }
}