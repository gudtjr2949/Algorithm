import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static String s;
    static String[] fullIps;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        printAnswer();
    }

    static void solve() {
        String[] arr = s.split(":");
        List<String> ipList = new ArrayList<>();

        for (int i = 0 ; i < arr.length ; i++) {
            String tmp = arr[i];
            if (tmp.length() == 0) continue;
            while (tmp.length() < 4) {
                tmp = "0" + tmp;
            }
            ipList.add(tmp);
        }

        int groupCnt = 8 - ipList.size() + 1;

        int idx = 0;

        for (String ip : ipList) {
            if (ip.equals("group")) {
                while (groupCnt-- > 0) {
                    fullIps[idx++] = "0000";
                }
            } else {
                fullIps[idx++] = ip;
            }
        }
    }

    static void printAnswer() {
        System.out.println(String.join(":", fullIps));
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        if (s.contains("::")) s = s.replace("::", ":group:");
        fullIps = new String[8];
    }
}