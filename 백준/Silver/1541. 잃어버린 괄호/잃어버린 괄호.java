import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int answer;
    static String s;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        String[] arr1 = s.split("-");
        for (String s1 : arr1) {
            String[] arr2 = s1.split("\\+");
            int sum = 0;
            for (String s2 : arr2) {
                sum += Integer.parseInt(s2);
            }
            list.add(sum);
        }

        answer = list.get(0);
        for (int i = 1 ; i < list.size() ; i++) {
            answer -= list.get(i);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        init();
    }

    static void init() {
        list = new ArrayList<>();
    }
}