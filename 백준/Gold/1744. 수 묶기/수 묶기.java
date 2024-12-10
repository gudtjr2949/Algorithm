import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(bf.readLine());

            if (num > 0) positive.add(num);
            else negative.add(num);
        }

        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        solve(positive);
        solve(negative);

        System.out.println(answer);
    }

    static void solve(List<Integer> list) {
        for (int i = 0 ; i < list.size() ; i++) {
            if (i+1 >= list.size()) {
                answer += list.get(i);
            } else {
                if (list.get(i) + list.get(i+1) > list.get(i) * list.get(i+1)) {
                    answer += list.get(i) + list.get(i+1);
                } else {
                    answer += list.get(i) * list.get(i+1);
                }

                i++;
            }
        }
    }
}