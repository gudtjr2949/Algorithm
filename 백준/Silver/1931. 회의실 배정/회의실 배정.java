import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static class Meeting implements Comparable<Meeting> {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

//        @Override
//        public int compareTo(Meeting m) {
//            if (m.end == this.end) {
//                return this.start - m.start;
//            } else {
//                return this.end - m.end;
//            }
//        }

        @Override
        public int compareTo(Meeting m) {
            if (this.end == m.end) {
                return this.start - m.start;
            } else {
                return this.end - m.end;
            }
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    static Meeting[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new Meeting[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[i] = new Meeting(start, end);
        }

        // 당연히 짧은 시간동안 회의실에서 회의를 하는게 이득임
        // 가장 중요한건 현재 진행중인 회의가 빨리 끝나는 거임 -> end가 작은 거
        // 그러므로 회의 종료를 기준으로 오름차순 정렬을 하고, 만약 회의 종료시간이 같은 회의라면 회의 시작시간이
        Arrays.sort(arr);

//        for (int i = 0 ; i < N ; i++) {
//            System.out.println(arr[i]);
//        }

        int lastEnd = 0;
        int answer = 0;

        for (int i = 0 ; i < N ; i++) {
            Meeting meeting = arr[i];

            // i번째 회의인 meeting 의 시작시간이 현재 가장 최근에 종료된 회의 이후일때
            if (lastEnd <= meeting.start) {
                answer++;
                lastEnd = meeting.end;
            }
        }

        System.out.println(answer);
    }

}