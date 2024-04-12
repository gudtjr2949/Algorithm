import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        String tmp = "";
        tmp += s.charAt(0);

        for (int i = 1 ; i < s.length() ; i++) {
            if (tmp.charAt(i-1) < s.charAt(i)) {
                tmp = s.charAt(i) + tmp;
            } else {
                tmp = tmp + s.charAt(i);
            }
        }

        StringBuilder sb = new StringBuilder(tmp);
        sb.reverse();
        System.out.println(sb.toString());
    }
}