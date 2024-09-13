import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        combi(0, 1);
        System.out.println(answer);
    }

    static void combi(int idx, int cur) {
        if (idx == M-N) { // M-N인 이유는 각 과일을 최소 1개는 훔쳐야 하기 때문임 -> 미리 훔침
            answer++;
            return;
        }

        for (int i = cur ; i <= N ; i++) {
            combi(idx+1, i);
        }
    }
}