import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int[][] prefixSum = new int[s.length()+1][26];

        prefixSum[1][s.charAt(0)-'a']++;

        for (int i = 2 ; i <= s.length() ; i++) {
            for (int j = 0 ; j < 26 ; j++) {
                prefixSum[i][j] = prefixSum[i-1][j];
            }
            prefixSum[i][s.charAt(i-1) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < q ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            char c = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken()) + 1;
            int r = Integer.parseInt(st.nextToken()) + 1;
            sb.append(prefixSum[r][c - 'a'] - prefixSum[l-1][c - 'a']).append("\n");
        }

        System.out.println(sb);
    }
}