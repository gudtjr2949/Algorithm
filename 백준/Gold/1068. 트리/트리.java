import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, root, answer;
    static boolean[] visited; // 숫자를 지우면서 방문한 노드
    static List<List<Integer>> tree;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        visited = new boolean[N];

        tree = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == -1) root = i;
            else tree.get(num).add(i);
        }

        int remove = Integer.parseInt(bf.readLine());
        visited[remove] = true;
        for (int i = 0 ; i < tree.get(remove).size() ; i++) removeNode(tree.get(remove).get(i));

        findLeaf(root);

        System.out.println(answer);
    }

    static void removeNode(int num) {
        visited[num] = true;

        for (int child : tree.get(num)) {
            if (!visited[child]) removeNode(child);
        }
    }

    static void findLeaf(int parent) {
        if (visited[parent]) return;

        visited[parent] = true;

        boolean isLeaf = true;
        if (tree.get(parent).size() == 0) answer++;
        else {
            for (int child : tree.get(parent)) {
                if (!visited[child]) {
                    findLeaf(child);
                    isLeaf = false;
                }
            }

            if (isLeaf) answer++;
        }
    }
}