package coding_test.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// 백준 14907 : 프로젝트 스케줄링
public class BOJ_14907 {

    static List<List<Node>> list;
    static int[] dist;
    static int start_idx, start_cost;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        start_idx = st.nextToken().charAt(0) - 'A';
        start_cost = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0 ; i < 26 ; i++) {
            list.add(new ArrayList<>());
        }

        String str = "";

        while(!(str = bf.readLine()).equals("")) {
            st = new StringTokenizer(str, " ");

            int to = st.nextToken().charAt(0) - 'A';
            int cost = Integer.parseInt(st.nextToken());
            String from = st.nextToken();

            for (int i = 0 ; i < from.length() ; i++) {
                list.get(from.charAt(i) - 'A').add(new Node(to, cost));
            }

            System.out.println("입력");
        }

        solve();
    }

    private static void solve() {
    }

    static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }
}
