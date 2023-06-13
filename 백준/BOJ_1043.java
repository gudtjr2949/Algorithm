package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 1043 : 거짓말
public class BOJ_1043 {

    static int N, M;
    static ArrayList<Integer> know;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());

        know = new ArrayList<>();

        list = new ArrayList<>();

        for (int i = 0; i <= M; i++) {
            list.add(new ArrayList<>());
        }

        if (!st.nextToken().equals("0")) {
            while (st.hasMoreTokens()) {
                know.add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(bf.readLine());

            int party_num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < party_num; j++) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        // 진실을 말해야하는지
        boolean[] truth = new boolean[N+1];

        for (int i = 0 ; i < know.size() ; i++) {
            truth[know.get(i)] = true;
        }

        for (int t = 0 ; t < N ; t++) {
            for (int i = 1; i <= N; i++) {
                if (truth[i]) {
                    for (int j = 1; j <= M; j++) {
                        if (list.get(j).contains(i)) {
                            for (int q = 0; q < list.get(j).size(); q++) {
                                truth[list.get(j).get(q)] = true;
                            }
                        }
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 1 ; i <= M ; i++) {
            boolean possible = true;
            for (int j = 0 ; j < list.get(i).size() ; j++) {
                if (truth[list.get(i).get(j)]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
