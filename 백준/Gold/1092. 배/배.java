import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[] crane;
    static List<Integer> box;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        crane = new int[N];

        int maxCrane = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
            maxCrane = Math.max(maxCrane, crane[i]);
        }

        M = Integer.parseInt(bf.readLine());
        box = new ArrayList<>();

        int maxBox = 0;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
            maxBox = Math.max(maxBox, box.get(i));
        }

        Arrays.sort(crane);

        if (maxCrane < maxBox) {
            answer = -1;
        } else {
            Collections.sort(box);

            while (!box.isEmpty()) {
                answer++;
                for (int i = 0; i < N; i++) {
                    int idx = binarySearch(crane[i]);
                    if (idx != -1) box.remove(idx);
                }
            }
        }

        System.out.println(answer);
    }

    static int binarySearch(int num) {
        int left = 0;
        int right = box.size();

        int result = -1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (box.get(mid) > num) {
                right = mid;
            } else { // 크레인 무게가 더 큼 -> 더 무거운 박스를 옮겨야 함
                result = mid;
                left = mid+1;
            }
        }

        return result;
    }
}