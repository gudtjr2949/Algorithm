import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static String operation;
    static List<Integer> list;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        sb = new StringBuilder();

        while (T-- > 0) {
            operation = bf.readLine();
            N = Integer.parseInt(bf.readLine());
            String s = bf.readLine();
            s = s.replaceAll("[\\[\\]]", "");
            StringTokenizer st = new StringTokenizer(s, ",");
            list = new ArrayList<>();
            while (st.hasMoreTokens()) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        boolean forward = true;

        for (int i = 0 ; i < operation.length() ; i++) {
            if (operation.charAt(i) == 'R') {
                forward = !forward;
            } else {
                if (list.size() == 0) {
                    sb.append("error").append("\n");
                    return;
                }

                if (forward) list.remove(0);
                else list.remove(list.size()-1);
            }
        }

        sb.append("[");

        if (forward && list.size() > 0) {
            // 정방향 : 앞 -> 뒤
            for (int i = 0 ; i < list.size() ; i++) {
                sb.append(list.get(i)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);

        } else if (!forward && list.size() > 0) {
            // 역방향 : 뒤 -> 앞
            for (int i = list.size()-1 ; i >= 0 ; i--) {
                sb.append(list.get(i)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("]").append("\n");
    }
}