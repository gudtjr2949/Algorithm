import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static String[] S, checkS;

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(answer);
    }

    static void binarySearch(String find) {
        int left = 0;
        int right = N-1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (S[mid].startsWith(find)) {
                answer++;
                return;
            } else if (S[mid].compareTo(find) < 0) { // S[mid]가 더 커져야 함
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) S[i] = bf.readLine();

        Arrays.sort(S);

        for (int i = 0 ; i < M ; i++) binarySearch(bf.readLine());
    }

    static void init() {
        S = new String[N];
        checkS = new String[M];
    }
}