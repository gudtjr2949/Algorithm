import java.io.*;
import java.util.*;

public class SWEA_15758 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++) {
            String a = bf.readLine();
            String[] s_arr = a.split(" ");
            String S = s_arr[0];
            String T = s_arr[1];

            int length = S.length() * T.length();

            String S_tmp = "";
            String T_tmp = "";

            while (true){
                S_tmp += S;
                if (S_tmp.length() == length)
                    break;
            }

            while (true){
                T_tmp += T;
                if (T_tmp.length() == length)
                    break;
            }

            String answer = "";

            if (S_tmp.equals(T_tmp))
                answer = "yes";
            else
                answer = "no";


            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
