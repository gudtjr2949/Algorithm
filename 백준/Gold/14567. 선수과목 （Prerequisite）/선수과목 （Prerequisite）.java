import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] edgeCnt = new int[N+1];
        int[] answer = new int[N+1];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            edgeCnt[B]++;
            adj.get(A).add(B);
        }

        Queue<Integer> Q = new LinkedList<>();

        for (int i = 1 ; i <= N ; i++) {
            if (edgeCnt[i] == 0) {
                Q.add(i);
            }
        }

        int semester = 0; // 학기수

        while (!Q.isEmpty()) {
            int size = Q.size();
            semester++;
            for (int i = 0 ; i < size ; i++) {
                int num = Q.poll();
                answer[num] = semester;
                for (int next : adj.get(num)) {
                    edgeCnt[next]--;
                    if (edgeCnt[next] == 0) {
                        Q.add(next);
                    }
                }
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            System.out.print(answer[i] + " ");
        }

    }
}