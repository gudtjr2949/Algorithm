import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] switches, origin;
    static Node[] nodes;
    static List<Integer> list;
    static Map<Integer, Integer> map;
    static class Node {
        int num, idx; // num = 스위치 번호, idx = 설치한 위치
        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        input(bf);
        solve();
        printAnswer();
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (list.isEmpty() || list.get(list.size()-1) < switches[i]) {
                list.add(switches[i]);
                nodes[i] = new Node(switches[i], list.size()-1);
            } else {
                int idx = find(switches[i]);
                nodes[i] = new Node(switches[i], idx);
                list.set(idx, switches[i]);
            }
        }
    }

    static int find(int key) {
        int left = 0;
        int right = list.size()-1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < key) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return left;
    }

    static void printAnswer() {
        int idx = list.size()-1;
        for (int i = N-1 ; i >= 0 ; i--) {
            if (nodes[i].idx == idx) {
                list.set(idx--, origin[nodes[i].num]);
            }
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (int num : list) System.out.print(num + " ");
    }

    static void input(BufferedReader bf) throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, i);
            origin[i] = num;
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(st.nextToken());
            switches[i] = map.get(num);
        }
    }

    static void init() {
        switches = new int[N];
        origin = new int[N];
        nodes = new Node[N];
        list = new ArrayList<>();
        map = new HashMap<>();
    }
}