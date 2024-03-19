import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long N, attackH;
    static Room[] roomArr;
    static class Room {
        long t, a, h;

        public Room(long t, long a, long h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        attackH = Long.parseLong(st.nextToken());

        roomArr = new Room[(int) N];

        for (int i = 0 ; i < (int) N ; i++) {
            st = new StringTokenizer(bf.readLine());
            long t = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            roomArr[i] = new Room(t, a, h);
        }

        System.out.println(binarySearch());
    }

    static long binarySearch() {
        long left = 0;
        long right = Long.MAX_VALUE;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2; // 내 생명력

            long result = solve(mid);

            if (result < N) { // 너무 나아간 방의 갯수가 적음 -> 생명력을 더 늘려야 함
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }

    // 내 생명력이 mid 일 때, 나아갈 수 있는 방의 갯수
    static long solve(long originH) {

        long tmpAttackH = attackH;
        long tmpH = originH;
        long cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            if (roomArr[i].t == 1) { // 몬스터 만남
                long result = (roomArr[i].h / tmpAttackH);
                tmpH -= (roomArr[i].h % tmpAttackH) == 0 ? (result - 1) * roomArr[i].a : result * roomArr[i].a;
                if (tmpH <= 0) return cnt;
            } else {
                tmpH += roomArr[i].h;
                if (tmpH >= originH) tmpH = originH;
                tmpAttackH += roomArr[i].a;
            }
            cnt++;
        }

        return cnt;
    }
}