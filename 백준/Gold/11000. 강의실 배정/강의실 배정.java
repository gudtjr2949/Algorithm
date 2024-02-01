import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Lesson implements Comparable<Lesson> {
        int S, T;

        public Lesson(int s, int t) {
            S = s;
            T = t;
        }

        @Override
        public int compareTo(Lesson l) {
            return this.S - l.S;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        Lesson[] lessons = new Lesson[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            lessons[i] = new Lesson(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lessons);

        // 강의 종료시간을 넣는 PQ -> 가장 종료시간이 임박한 강의를 빠르게 찾기위해 PQ 사용
        PriorityQueue<Integer> PQ = new PriorityQueue<>();

        PQ.add(lessons[0].T);

        for (int i = 1 ; i < N ; i++) {
            int endTime = PQ.peek();

            // 남아있는 강의중, 가장 시작시간이 이른 강의의 시작시간보다 종료시간이 가장 얼마 안남은 강의의 종료시간이 더 이른 경우 -> 강의실 재활용 가능
            if (endTime <= lessons[i].S) {
                PQ.poll();
            }
            PQ.add(lessons[i].T);
        }

        System.out.println(PQ.size());
    }
}