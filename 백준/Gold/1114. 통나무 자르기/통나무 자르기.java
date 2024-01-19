import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        String[] spt = line.split(" ");
        Long l = Long.parseLong(spt[0]);
        int k = Integer.parseInt(spt[1]);
        int c = Integer.parseInt(spt[2]);

        line = sc.nextLine();
        spt = line.split(" ");
        List<Long> pos = new ArrayList<>();
        pos.add(0L);
        pos.add(l);
        for (int i = 0; i < k; i++) {
            pos.add(Long.parseLong(spt[i]));
        }
        pos.sort((a, b) -> (int) (a - b));
        long left = 0;
        long right = l;
        long ansFirst = 0;
        long ansLongest = l;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long cutCnt = 0;
            long firstCut = -1;
            long diff = 0;
            for (int i = k; i >= 0; i--) {
                diff += pos.get(i + 1) - pos.get(i);
                if (diff > mid) {
                    diff = pos.get(i + 1) - pos.get(i);
                    cutCnt++;
                    if (diff > mid) {
                        cutCnt = c + 1;
                        break;
                    }
                }
            }
            // 첫번째를 자를 수 있는지 확인한다.
            // 한번이라도 자를 수 있으면 첫번째가 최소 길이가 된다.
            if (cutCnt < c) {
                firstCut = pos.get(1);
            } else {
                firstCut = diff;
            }
            if (cutCnt <= c) {
                ansLongest = Math.min(mid, ansLongest);
                ansFirst = firstCut;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ansLongest + " " + ansFirst);
        sc.close();

    }
}