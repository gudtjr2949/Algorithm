import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int P, M;
    static Player[] players;
    static StringBuilder sb = new StringBuilder();

    static class Player implements Comparable<Player> {
        int level;
        String nickname;
        boolean check;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }


        @Override
        public int compareTo(Player p) {
            return this.nickname.compareTo(p.nickname);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        P = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        players = new Player[P];

        for (int i = 0 ; i < P ; i++) {
            st = new StringTokenizer(bf.readLine());
            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();
            players[i] = new Player(level, nickname);
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0 ; i < P ; i++) {
            List<Player> room = new ArrayList<>();

            if (!players[i].check) {
                for (int j = 0 ; j < P ; j++) {
                    if (room.size() == M) break;

                    if (!players[j].check && Math.abs(players[i].level - players[j].level) <= 10) {
                        players[j].check = true;
                        room.add(players[j]);
                    }
                }

                Collections.sort(room);

                if (room.size() == M) {
                    sb.append("Started!").append("\n");

                    for (Player player : room) {
                        sb.append(player.level).append(" ").append(player.nickname).append("\n");
                    }
                } else {
                    sb.append("Waiting!").append("\n");

                    for (Player player : room) {
                        sb.append(player.level).append(" ").append(player.nickname).append("\n");
                    }
                }
            }
        }
    }
}