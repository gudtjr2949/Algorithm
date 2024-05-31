import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        String pattern = "(100+1+|01)+";
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            String s = bf.readLine();
            sb.append(s.matches(pattern) ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }
}