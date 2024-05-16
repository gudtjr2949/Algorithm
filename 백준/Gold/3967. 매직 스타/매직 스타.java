import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static char[][] map = new char[5][9];
    static boolean[] visited = new boolean[13];
    static List<Node> list = new ArrayList<>();
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0 ; i < 5 ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < 9 ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'x') list.add(new Node(j ,i));
                else if (map[i][j] != '.') visited[map[i][j] - 'A'] = true;
            }
        }
        
        dfs(0);
    }

    static void dfs(int idx) {
        if (idx == list.size()) {
            if (check()) {
                for (int i = 0 ; i < 5 ; i++) {
                    for (int j = 0 ; j < 9 ; j++) {
                        System.out.print(map[i][j]);
                    }
                    System.out.println();
                }
                System.exit(0);
            }
            return;
        }

        for (int i = 0 ; i < 12 ; i++) {
            if (!visited[i]) {
                Node now = list.get(idx);
                map[now.y][now.x] = (char) (i + 'A');
                visited[i] = true;
                dfs(idx+1);
                visited[i] = false;
                map[now.y][now.x] = '.';
            }
        }
    }

    static boolean check() {
        int sum1 = (map[0][4]-'A'+1) + (map[1][3]-'A'+1) + (map[2][2]-'A'+1) + (map[3][1]-'A'+1);
        int sum2 = (map[0][4]-'A'+1) + (map[1][5]-'A'+1) + (map[2][6]-'A'+1) + (map[3][7]-'A'+1);
        int sum3 = (map[1][1]-'A'+1) + (map[1][3]-'A'+1) + (map[1][5]-'A'+1) + (map[1][7]-'A'+1);
        int sum4 = (map[1][1]-'A'+1) + (map[2][2]-'A'+1) + (map[3][3]-'A'+1) + (map[4][4]-'A'+1);
        int sum5 = (map[3][1]-'A'+1) + (map[3][3]-'A'+1) + (map[3][5]-'A'+1) + (map[3][7]-'A'+1);
        int sum6 = (map[1][7]-'A'+1) + (map[2][6]-'A'+1) + (map[3][5]-'A'+1) + (map[4][4]-'A'+1);

        if (sum1 == 26 && sum2 == 26 && sum3 == 26 && sum4 == 26 && sum5 == 26 && sum6 == 26)
            return true;
        return false;
    }
}