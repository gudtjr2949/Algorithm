import java.io.*;

// 백준 2798 : 블랙잭
public class BOJ_2798 {
    /*
    * 조건 1 : 카드의 갯수는 3개
    * 조건 2 : 그 3장의 카드의 합은 M보다 작거나 같아야함
    * 조건 3 : 조건 2에서 구한 카드의 합 중 가장 큰 값이 답 */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        String[] s_arr = s.split(" ");
        int N = Integer.parseInt(s_arr[0]);
        int M = Integer.parseInt(s_arr[1]);

        String a = bf.readLine();
        String[] a_arr = a.split(" ");

        int[] arr = new int[N];

        for (int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(a_arr[i]);
        }

        int answer = 0;

        // 조건 1 : 모든 3장의 경우를 뽑기 위해 3중 for 문
        for (int i = 0 ; i < N-2 ; i++){
            for (int j = i+1 ; j < N-1 ; j++){
                for (int q = j+1 ; q < N ; q++){
                    int sum = arr[i] + arr[j] + arr[q];
                    if (sum <= M) // 조건 2
                        answer = Math.max(answer, sum); // 조건 3
                }
            }
        }

        System.out.println(answer);
    }
}
