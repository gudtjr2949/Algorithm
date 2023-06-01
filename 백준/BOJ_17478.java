package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 17478 : 재귀함수가 뭔가요?
public class BOJ_17478 {
    static String[] arr = {"어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.",
            "\"재귀함수가 뭔가요?\"",
            "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
            "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
            "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"",
            "\"재귀함수는 자기 자신을 호출하는 함수라네\"",
            "라고 답변하였지." };

    static int N;
    static StringBuilder sb = new StringBuilder();
    static StringBuilder sb_2 = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        String s = "";

        System.out.println(arr[0]);
        recursive(0, 1, s);
        System.out.print(sb);
        System.out.print(sb_2);
        System.out.println(arr[6]);
    }

    private static void recursive(int cnt, int idx, String s) {
        // basis
        if (cnt == N) {
            sb.append(s).append(arr[5]).append("\n");
            return;
        }

        if (idx == arr.length - 2) {
            cnt++;
            s += "____";
            String tmp = s + arr[6] + "\n";
            sb_2.insert(0, tmp);
            idx = 1;
        }

        // logic
        sb.append(s).append(arr[idx]).append("\n");

        // inductive
        recursive(cnt, idx + 1, s);
    }
}
