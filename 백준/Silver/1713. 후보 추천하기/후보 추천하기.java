import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static Picture[] pictures;
    static class Picture {
        int num, cnt, time;

        public Picture(int num, int cnt, int time) {
            this.num = num;
            this.cnt = cnt;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        pictures = new Picture[N];
        for (int i = 0 ; i < N ; i++)
            pictures[i] = new Picture(0, 0, 0);

        int M = Integer.parseInt(bf.readLine());
        arr = new int[M];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < M ; i++) {
            solve(Integer.parseInt(st.nextToken()), i);
            Arrays.sort(pictures, new Comparator<Picture>() {
                @Override
                public int compare(Picture o1, Picture o2) {
                    if (o1.cnt == o2.cnt) return o1.time - o2.time;
                    else return o1.cnt - o2.cnt;
                }
            });
        }

        Arrays.sort(pictures, new Comparator<Picture>() {
            @Override
            public int compare(Picture o1, Picture o2) {
                return o1.num - o2.num;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < N ; i++) {
            if (pictures[i].num == 0) continue;
            sb.append(pictures[i].num).append(" ");
        }

        System.out.println(sb);
    }

    static void solve(int now, int time) {
        for (int i = 0 ; i < N ; i++) {
            if (pictures[i].num == now) {
                pictures[i].cnt++;
                return;
            }
        }

        for (int i = 0 ; i < N ; i++) {
            if (pictures[i].num == 0) {
                pictures[i] = new Picture(now, 1, time);
                return;
            }
        }

        pictures[0] = new Picture(now, 1, time);
    }
}