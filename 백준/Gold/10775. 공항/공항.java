import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int G, P, answer;
    static int[] parents, airplanes;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < P ; i++) {
            int findGate = find(airplanes[i]);
            if (findGate > 0) {
                parents[findGate]--;
                answer++;
            } else if (findGate == 0) {
                return;
            }
        }
    }

    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }
    
    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(bf.readLine());
        P = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < P ; i++) {
            airplanes[i] = Integer.parseInt(bf.readLine());
        }
    }

    static void init() {
        parents = new int[G+1];
        for (int i = 0 ; i <= G ; i++) parents[i] = i;
        airplanes = new int[P];
    }
}