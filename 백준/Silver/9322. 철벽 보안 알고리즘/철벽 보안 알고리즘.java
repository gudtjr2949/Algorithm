import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int before, after;

        public Node(int before, int after) {
            this.before = before;
            this.after = after;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(bf.readLine());
            String[] firstKey = new String[N];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0 ; i < N ; i++) {
                firstKey[i] = st.nextToken();
            }

            String[] secondKey = new String[N];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0 ; i < N ; i++) {
                secondKey[i] = st.nextToken();
            }

            Node[] rule = new Node[N];
            int idx = 0;

            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    if (firstKey[i].equals(secondKey[j])) {
                        rule[idx++] = new Node(i, j);
                    }
                }
            }

            String[] encryption = new String[N];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0 ; i < N ; i++) {
                encryption[i] = st.nextToken();
            }

            String[] decryption = new String[N];

            for (int i = 0 ; i < N ; i++) {
                int from = rule[i].before;
                int to = rule[i].after;
                decryption[from] = encryption[to];
            }

            for (int i = 0 ; i < N ; i++) {
                sb.append(decryption[i]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}