import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 0 ; T < Test ; T++) {
            N = Integer.parseInt(bf.readLine());

            arr = new int[N];

            list = new ArrayList<>();

            for (int i = 0 ; i < N ; i++) {
                arr[i] = Integer.parseInt(bf.readLine());
            }

            list.add(arr[0]);

            for (int i = 1 ; i < N ; i++) {
                if (arr[i] > list.get(list.size()-1)) {
                    list.add(arr[i]);
                } else {
                    int idx = binarySearch(0, list.size(), arr[i]);
                    list.set(idx, arr[i]);
                }
            }

            sb.append(list.size()).append("\n");
        }

        System.out.println(sb);
    }

    static int binarySearch(int left, int right, int key) {
        int idx = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < key) {
                left = mid+1;
            } else {
                idx = mid;
                right = mid-1;
            }
        }

        return idx;
    }
}