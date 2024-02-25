import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] A = new int[N];
        List<Integer> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        list.add(Integer.MIN_VALUE);

        for (int i = 0 ; i < N ; i++) {
            int num = A[i];
            int left = 0;
            int right = list.size()-1;

            if (num > list.get(right)) {
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

        System.out.println(list.size()-1);
    }
}