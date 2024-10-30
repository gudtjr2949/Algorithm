import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String s;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        visited = new boolean[s.length()];

        dfs(0, s.length());
    }

    static boolean check() {
        for (int i = 0 ; i < visited.length ; i++) {
            if (!visited[i]) return true;
        }

        return false;
    }

    static void dfs(int left, int right) {
        int minIdx = 0;
        char minChar = 'a';

        for (int j = left ; j < right ; j++) {
            if (s.charAt(j) < minChar && !visited[j]) {
                minChar = s.charAt(j);
                minIdx = j;
            }
        }
        if (minChar == 'a') return;
        visited[minIdx] = true;
        print();
        dfs(minIdx, right);
        dfs(left, minIdx);
    }

    static void print() {
        for (int i = 0 ; i < s.length() ; i++) {
            if (visited[i]) {
                System.out.print(s.charAt(i));
            }
        }
        System.out.println();
    }
}