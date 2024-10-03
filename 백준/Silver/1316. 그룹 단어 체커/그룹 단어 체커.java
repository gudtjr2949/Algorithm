import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int answer = 0;

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            boolean possible = true;
            Set<Character> set = new HashSet<>();
            set.add(s.charAt(0));
            for (int j = 1 ; j < s.length() ; j++) {
                if (s.charAt(j-1) == s.charAt(j)) continue;
                else if (!set.contains(s.charAt(j))) set.add(s.charAt(j));
                else {
                    possible = false;
                    break;
                }
            }

            if (possible) answer++;
        }

        System.out.println(answer);
    }
}