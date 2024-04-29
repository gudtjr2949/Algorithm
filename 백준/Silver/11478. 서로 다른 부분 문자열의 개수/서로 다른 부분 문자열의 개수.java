import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        Set<String> set = new HashSet<>();

        for (int i = 0 ; i < s.length() ; i++) {
            for (int j = i+1 ; j <= s.length() ; j++) {
                set.add(s.substring(i, j));
            }
        }

        System.out.println(set.size());
    }

}