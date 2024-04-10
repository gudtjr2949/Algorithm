import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, lectureRoom;
    static Queue<Class> PQ = new PriorityQueue<>((c1, c2) -> {
        if (c1.end != c2.end)
            return c1.end - c2.end;
        else
            return c1.start - c2.start;
    });
    static Class[] classes;

    static class Class implements Comparable<Class>{
        int start, end;

        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Class c) {
            if (this.start != c.start)
                return this.start - c.start;
            else
                return this.end - c.end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        classes  = new Class[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            classes[i] = new Class(start, end);
        }

        solve();

        System.out.println(lectureRoom);
    }

    static void solve() {
        // 시작 순으로 정렬
        Arrays.sort(classes);

        // 첫 강의 넣기
        PQ.add(classes[0]);

        // 생성된 강의실
        lectureRoom = 1;

        for (int i = 1 ; i < classes.length ; i++) {
            // 강의중인 강의실이 있는 경우
            if (!PQ.isEmpty()) {
                // 현재 진행중인 강의
                Class c = PQ.peek();
                int nowStart = c.start;
                int nowEnd = c.end;

                // 다음 강의
                int nextStart = classes[i].start;
                int nextEnd = classes[i].end;

                // 만약에 다음 강의와 겹치지 않는 경우? -> 현재 강의실 재활용
                // 현재 진행중인 강의 종료
                if (nowEnd <= nextStart) {
                    PQ.remove();
                    PQ.add(new Class(nextStart, nextEnd));
                }
                // 다음 강의와 겹치는데 현재 빈 강의실(이미 만들어진 강의실)이 있는 경우 -> PQ.size() < lectureRoom
                else if (PQ.size() < lectureRoom) {
                    PQ.add(new Class(nextStart, nextEnd));
                }
                // 진행하고 있는 강의가 아직 끝나지 않았는데, 다음 강의가 시작해야하는 경우 -> 강의실 하나 더 개설
                else {
                    PQ.add(new Class(nextStart, nextEnd));
                    lectureRoom++;
                }
            } else { // 강의실이 텅 빈 경우
                PQ.add(new Class(classes[i].start, classes[i].end));
            }
        }
    }
}