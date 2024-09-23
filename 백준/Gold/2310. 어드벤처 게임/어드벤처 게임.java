import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean possible;
    static boolean[] visited;
    static Node[] rooms;
    static class Node {
        char form;
        int price;
        List<Integer> room;

        public Node(char form, int price, List<Integer> room) {
            this.form = form;
            this.price = price;
            this.room = room;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "form=" + form +
                    ", price=" + price +
                    ", room=" + room +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            N = Integer.parseInt(bf.readLine());
            if (N == 0) break;
            visited = new boolean[N+1];
            possible = false;

            rooms = new Node[N+1];
            for (int i = 1 ; i <= N ; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                char form = st.nextToken().charAt(0);
                int price = Integer.parseInt(st.nextToken());
                List<Integer> room = new ArrayList<>();
                while (true) {
                    int nextIdx = Integer.parseInt(st.nextToken());
                    if (nextIdx == 0) break;
                    room.add(nextIdx);
                }
                rooms[i] = new Node(form, price, room);
            }

            dfs(1, 0);

            if (possible) sb.append("Yes").append("\n");
            else sb.append("No").append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int idx, int curMoney) {
        char form = rooms[idx].form;
        int price = rooms[idx].price;

        if (idx == N && form == 'T' && curMoney >= price) {
            possible = true;
            return;
        }

        if (possible) return;

        if (form == 'E') {
            for (Integer next : rooms[idx].room) {
                if (!visited[next]) {
                    visited[next] = true;
                    dfs(next, curMoney);
                    visited[next] = false;
                }
            }
        } else if (form == 'L') {
            curMoney = curMoney < price ? price : curMoney;
            for (Integer next : rooms[idx].room) {
                if (!visited[next]) {
                    visited[next] = true;
                    dfs(next, curMoney);
                    visited[next] = false;
                }
            }
        } else {
            for (Integer next : rooms[idx].room) {
                if (!visited[next] && curMoney >= price) {
                    visited[next] = true;
                    dfs(next, curMoney - price);
                    visited[next] = false;
                }
            }
        }
    }
}