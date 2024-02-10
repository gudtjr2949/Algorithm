import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, childA, childB;
    static boolean[] visited;
    static List<List<Integer>> tree;
    static List<Integer> listA, listB;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 0; T < Test; T++) {
            N = Integer.parseInt(bf.readLine());

            tree = new ArrayList<>();

            visited = new boolean[N + 1];

            for (int i = 0; i <= N; i++) {
                tree.add(new ArrayList<>());
            }

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());

                int parents = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                tree.get(child).add(parents);
            }

            StringTokenizer st = new StringTokenizer(bf.readLine());

            childA = Integer.parseInt(st.nextToken());
            childB = Integer.parseInt(st.nextToken());

            listA = new ArrayList<>();
            listB = new ArrayList<>();

            listA.add(childA);
            listB.add(childB);

            dfs(childA, listA);
            dfs(childB, listB);

            Loop:
            for (Integer A : listA) {
                for (Integer B : listB) {
                    if (A.equals(B)) {
                        sb.append(A).append("\n");
                        break Loop;
                    }
                }
            }
        }

        System.out.println(sb);
    }

    static void dfs(int child, List<Integer> list) {
        for (Integer parents : tree.get(child)) {
            list.add(parents);
            dfs(parents, list);
        }
    }
}