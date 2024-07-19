import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> sortedList;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new int[N];
        sortedList = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;

            if (!set.contains(num)) {
                set.add(num);
                sortedList.add(num);
            }
        }

        Collections.sort(sortedList);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(binarySearch(arr[i])).append(" ");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int num) {
        int left = 0;
        int right = sortedList.size();
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if(num <= sortedList.get(mid)) {
                result = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }

        return result;
    }
}