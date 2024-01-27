import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = 0;
    static Integer[] crane;
    static ArrayList<Integer> box;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        crane = new Integer[N];

        for (int i = 0 ; i < N ; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(bf.readLine());

        box = new ArrayList<>();

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        // 가장 무거운 박스의 무게가 가장 무거운 박스를 들 수 있는 크레인의 제한 무게보다 무거운 경우
        if (crane[0] < box.get(0)) {
            System.out.println(-1);
        } else {
            solve();
            System.out.println(answer);
        }
    }

    // 무거운 박스를 들 수 있는 크레인은 무거운 박스를 우선으로 들어야 함
    // 박스를 들었다면, 그 박스는 box 리스트에서 remove 해야함
    static void solve() {
        while (!box.isEmpty()) {
            int boxIdx = 0;
            for (int i = 0 ; i < N ; ) {
                if (boxIdx == box.size())
                    break;
                
                 if(box.get(boxIdx) <= crane[i]) {
                    box.remove(boxIdx);
                    i++;
                } else {
                    boxIdx++;
                }
            }

            answer++;
        }
    }
}