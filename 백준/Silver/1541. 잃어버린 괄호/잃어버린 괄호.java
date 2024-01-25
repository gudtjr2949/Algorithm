import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        int answer = Integer.MAX_VALUE;

        String[] arr = s.split("-"); // 더하기끼리 모으기

        for (int i = 0 ; i < arr.length ; i++) {
            String[] tmpArr = arr[i].split("\\+");

            int sum = 0;

            for (int j = 0 ; j < tmpArr.length ; j++) {
                sum += Integer.parseInt(tmpArr[j]);
            }

            if (answer == Integer.MAX_VALUE) {
                answer = sum;
            } else {
                answer -= sum;
            }
        }

        System.out.println(answer);
    }
}