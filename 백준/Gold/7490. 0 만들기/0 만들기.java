import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static char[] operations;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        input();
        System.out.println(sb);
    }

    static void dfs(int idx) {
        if (idx == N) {
            check();
            return;
        }

        operations[idx] = ' ';
        dfs(idx+1);

        operations[idx] = '+';
        dfs(idx+1);

        operations[idx] = '-';
        dfs(idx+1);
    }

    static void check() {
        List<Integer> numList = new ArrayList<>();
        List<Character> operList = new ArrayList<>();

        numList.add(1);

        for (int i = 1 ; i < N ; i++) {
            if (operations[i] == ' ') {
                numList.set(numList.size()-1, numList.get(numList.size()-1) * 10 + (i+1));
            } else {
                numList.add(i+1);
                operList.add(operations[i]);
            }
        }

        int result = numList.get(0);

        for (int i = 0 ; i < operList.size() ; i++) {
            if (operList.get(i) == '+') {
                result += numList.get(i+1);
            } else if (operList.get(i) == '-') {
                result -= numList.get(i+1);
            }
        }

        if (result == 0) {
            sb.append(1);
            for (int i = 1 ; i < N ; i++) {
                sb.append(operations[i]).append(i+1);
            }
            sb.append("\n");
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            init();
            dfs(1);
            sb.append("\n");
        }

    }

    static void init() {
        operations = new char[N];
    }
}