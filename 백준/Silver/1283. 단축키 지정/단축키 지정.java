import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    static int N;
    static String[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new String[N];
        sb = new StringBuilder();

        for (int i = 0 ; i < N ; i++) {
            arr[i] = bf.readLine();
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        Set<Character> set = new HashSet<>();
        Map<String, Character> map = new HashMap<>();

        for (int i = 0 ; i < N ; i++) {
            String[] tmp = arr[i].split(" ");
            boolean find = false;

            for (int j = 0 ; j < tmp.length ; j++) {
                String s = tmp[j];

                if (map.containsKey(s)) continue;

                if (!set.contains(Character.toUpperCase(s.charAt(0))) && !set.contains(Character.toLowerCase(s.charAt(0)))) {
                    find = true;
                    map.put(s, s.charAt(0));
                    set.add(s.charAt(0));
                    setString(tmp, j, 0);
                    break;
                }
            }

            if (!find) {
                // 모든 뭉치의 알파벳이 존재하는지 확인하고, 만약 해당 알파벳에 할당된 단축키가 없으면 set 에 추가
                Loop:
                for (int j = 0 ; j < tmp.length ; j++) {
                    String s = tmp[j];
                    for (int k = 0 ; k < s.length() ; k++) {
                        if (s.charAt(k) == ' ') continue;

                        if (!set.contains(Character.toUpperCase(s.charAt(k))) && !set.contains(Character.toLowerCase(s.charAt(k)))) {
                            find = true;
                            set.add(s.charAt(k));
                            setString(tmp, j, k);
                            break Loop;
                        }
                    }
                }

                if (!find)
                    sb.append(arr[i]);
            }

            sb.append("\n");
        }
    }

    static void setString(String[] tmp, int firstIdx, int secondIdx) {
        for (int i = 0 ; i < tmp.length ; i++) {
            if (i == firstIdx) {
                for (int j = 0 ; j < tmp[i].length() ; j++) {
                    if (j == secondIdx) {
                        sb.append("[").append(tmp[i].charAt(j)).append("]");
                    } else {
                        sb.append(tmp[i].charAt(j));
                    }
                }
                sb.append(" ");
            } else {
                sb.append(tmp[i]).append(" ");
            }
        }
    }
}