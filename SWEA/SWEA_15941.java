import java.io.*;
import java.util.*;

// SWEA 15941 : 평행사변형
public class SWEA_15941 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++) {
            int N = Integer.parseInt(bf.readLine());

            sb.append("#").append(i+1).append(" ").append(N*N).append("\n");
        }
        System.out.println(sb);
    }
}
