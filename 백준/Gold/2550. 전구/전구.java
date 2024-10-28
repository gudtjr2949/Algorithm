import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr, input;
    static List<Integer> list;
    static Map<Integer, Integer> map1, map2;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        input = new int[N];
        list = new ArrayList<>();
        map1 = new HashMap<>();
        map2 = new HashMap<>();

        int idx = 0;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(st.nextToken());
            map1.put(num, idx);
            map2.put(idx++, num);
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = map1.get(Integer.parseInt(st.nextToken()));
        }

        solve();

        List<Integer> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        idx = list.size() - 1;

        for (int i = N-1 ; i >= 0 ; i--) {
            if (idx < 0) break;
            if (idx == input[i]) {
                answer.add(map2.get(i));
                idx--;
            }
        }

        Collections.sort(answer);
        for (Integer num : answer) {
            sb.append(num).append(" ");
        }

        System.out.println(list.size());
        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (list.size() == 0 || list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
                input[arr[i]] = list.size()-1;
            } else {
                int idx = findIdx(arr[i]);
                list.set(idx, arr[i]);
                input[arr[i]] = idx;
            }
        }

    }

    static int findIdx(int target) {
        int left = 0;
        int right = list.size()-1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}