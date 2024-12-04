import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[] crane;
    static List<Integer> box;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        crane = new int[N];

        int maxCrane = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
            maxCrane = Math.max(maxCrane, crane[i]);
        }

        M = Integer.parseInt(bf.readLine());
        box = new ArrayList<>();

        int maxBox = 0;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < M ; i++) {
            box.add(Integer.parseInt(st.nextToken()));
            maxBox = Math.max(maxBox, box.get(i));
        }

        Arrays.sort(crane);

        if (maxCrane < maxBox) {
            answer = -1;
        } else {
            Collections.sort(box, Collections.reverseOrder());

            while (!box.isEmpty()) {
                answer++;
                solve();
            }
        }

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < box.size() ; j++) {
                if (crane[i] >= box.get(j)) {
                    box.remove(j);
                    break;
                }
            }
        }
    }
}