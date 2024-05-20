import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Set<String> keywords = new HashSet<>();
    static Set<String> used = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < N ; i++) {
            keywords.add(bf.readLine());
        }

        int size = N;

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine(), ",");
            int length = st.countTokens();
            for (int j = 0 ; j < length ; j++) {
                String s = st.nextToken();
                if (keywords.contains(s) && !used.contains(s)) {
                    used.add(s);
                    size--;
                }
            }

            sb.append(size).append("\n");
        }

        System.out.println(sb);
    }
}