import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    static int N;
    static List<String> list, backjoon;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        list = new ArrayList<>();
        backjoon = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();

            if (s.startsWith("boj.kr/")) backjoon.add(s);
            else list.add(s);
        }

        solve();

        StringBuilder sb = new StringBuilder();

        for (String s : list) sb.append(s).append("\n");
        for (String s : backjoon) sb.append(s).append("\n");

        System.out.println(sb);
    }

    static void solve() {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) return s1.compareTo(s2);
                else return s1.length() - s2.length();
            }
        });

        Collections.sort(backjoon, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.parseInt(s1.replace("boj.kr/", ""))
                        - Integer.parseInt(s2.replace("boj.kr/", ""));
            }
        });
    }
}