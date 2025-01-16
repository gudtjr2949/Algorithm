import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int K, Q;
    static long N;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        input1(bf);
        init();
        input2(bf);
        System.out.println(sb);
    }

    static long solve(long X, long Y) {
        long movingCnt = 0; // 움직임 횟수

        if (getDepth(X) > getDepth(Y)) {
            while (getDepth(X) != getDepth(Y)) {
                X = getParent(X);
                movingCnt++;
            }
        } else if (getDepth(X) < getDepth(Y)) {
            while (getDepth(X) != getDepth(Y)) {
                Y = getParent(Y);
                movingCnt++;
            }
        }

        while (X != Y) {
            X = getParent(X);
            Y = getParent(Y);
            movingCnt+=2;
        }

        return movingCnt;
    }

    static long getParent(long idx) {
        return (idx-2) / K+1;
    }

    static long getDepth(long idx) {
        if(idx == 1) return 0;

        long line = 1;
        long h = 1;

        while(true) {
            line += (long)Math.pow(K, h++);
            if(idx <= line) break;
        }

        return h-1;
    }

    static void input1(BufferedReader bf) throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
    }

    static void input2(BufferedReader bf) throws Exception {
        for (int i = 0 ; i < Q ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            long X = Long.parseLong(st.nextToken());
            long Y = Long.parseLong(st.nextToken());
            if (K == 1) {
                sb.append(Math.abs(X - Y)).append("\n");
            } else {
                sb.append(solve(X, Y)).append("\n");
            }
        }
    }

    static void init() {
        sb = new StringBuilder();
    }
}