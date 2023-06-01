import java.io.*;

public class SWEA_7272 {

    static int[] alpha = {1, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++){
            String s = bf.readLine();
            String[] s_arr = s.split(" ");
            String s1 = s_arr[0];
            String s2 = s_arr[1];

            int shorts = 0;

            boolean sign = true;

            if (s1.length() == s2.length()){
                for (int q = 0 ; q < s1.length() ; q++){
                    if (alpha[s1.charAt(q) - 'A'] == alpha[s2.charAt(q) - 'A']){
                        continue;
                    }

                    else {
                        sign = false;
                        break;
                    }
                }
            }

            else {
                sign = false;
            }

            String answer = "";

            if (sign)
                answer = "SAME";
            else
                answer = "DIFF";

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
