import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        String pattern = "(100+1+|01)+";

        System.out.println(s.matches(pattern) ? "SUBMARINE" : "NOISE");
    }
}