import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        List<Integer> list = new ArrayList<>();

        list.add(arr[0]);

        for (int i = 0 ; i < N ; i++) {
            int num = arr[i];
            int left = 0;
            int right = list.size()-1;

            if (list.get(right) < num) {
                list.add(num);
            } else {
                int idx = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (list.get(mid) < num) {
                        left = mid+1;
                    } else {
                        idx = mid;
                        right = mid-1;
                    }
                }

                list.set(idx, num);
            }
        }

        System.out.println(N-list.size());
    }
}