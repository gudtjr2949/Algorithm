import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static String[] arr;
    static List<String> list;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(list.size());
    }

    static void solve() {
        list.add(arr[0]);

        for (int i = 1 ; i < N ; i++) {
            boolean flag = false;
            for (String s : list) {
                if (isCycle(s, arr[i])) {
                    flag = true;
                    break;
                }
            }

            if (!flag) list.add(arr[i]);
        }
    }

    static boolean isCycle(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        for (int i = 1 ; i < s2.length() ; i++) {
            int idx = i;
            String tmp = "";
            while (tmp.length() != s2.length()) {
                tmp += s2.charAt(idx++);
                if (idx == s2.length()) idx = 0;
            }

            if (s1.equals(tmp)) {
                return true;
            }
        }

        return false;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) arr[i] = bf.readLine();
    }

    static void init() {
        list = new ArrayList<>();
        arr = new String[N];
    }
}