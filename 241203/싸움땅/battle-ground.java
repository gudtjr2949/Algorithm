import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0}, score;
    static List<Integer>[][] map;
    static Player[] players;
    static class Player {
        int idx, x, y, dir, s, gun;
        public Player(int idx, int x, int y, int dir, int s, int gun) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.s = s;
            this.gun = gun;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];

        players = new Player[M];
        score = new int[M];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = new ArrayList<>();
                int gun = Integer.parseInt(st.nextToken());

                if (gun != 0)
                    map[i][j].add(gun);
            }
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());

            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            players[i] = new Player(i, x, y, dir, s, 0);
        }

        for (int i = 0 ; i < K ; i++) play();

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < M ; i++) sb.append(score[i]).append(" ");

        System.out.println(sb);
    }

    static void play() {
        for (int i = 0 ; i < M ; i++) {
            Player p = players[i];
            
            int nx = p.x + dx[p.dir];
            int ny = p.y + dy[p.dir];

            if (!isRange(nx, ny)) {
				p.dir = (p.dir + 2) % 4;
				
				nx = p.x + dx[p.dir];
				ny = p.y + dy[p.dir];
			}

            Player next = findPlayer(nx, ny);
            
            p.x = nx;
            p.y = ny;

            if (next == null) {
                takeGun(p);
            } else {
                fight(p, next);
            }
        }
    }

    static void fight(Player p1, Player p2) {
        int p1Power = p1.s + p1.gun;
        int p2Power = p2.s + p2.gun;

        // P1 승리
        if (p1Power > p2Power || (p1Power == p2Power && p1.s > p2.s)) {
            score[p1.idx] += (p1Power - p2Power);
            lose(p2);
            takeGun(p1);
        } else {
            score[p2.idx] += (p2Power - p1Power);
            lose(p1);
            takeGun(p2);
        }
    }

	static void lose(Player p) {
		map[p.y][p.x].add(p.gun);
		p.gun = 0;
		
		for (int i = 0 ; i < 4 ; i++) {
			int nd = (p.dir + i) % 4;
			
			int nx = p.x + dx[nd];
			int ny = p.y + dy[nd];
			
			if (isRange(nx, ny) && findPlayer(nx, ny) == null) {
				p.x = nx;
				p.y = ny;
				p.dir = nd;
				
				takeGun(p);
				
				break;
			}
		}
	}

    static void takeGun(Player p) {
        if (p.gun > 0) {
			map[p.y][p.x].add(p.gun);
		}
		
		if (!map[p.y][p.x].isEmpty()) {
			Collections.sort(map[p.y][p.x]);
			
			p.gun = map[p.y][p.x].get(map[p.y][p.x].size() - 1);
			map[p.y][p.x].remove(map[p.y][p.x].size() - 1);
		}
    }

    static Player findPlayer(int x, int y) {
		for (int i = 0 ; i < M ; i++) {
			Player p = players[i];
			
			if (p.x == x && p.y == y) {
				return p;
			}
		}
		
		return null;
	}

    static boolean isRange(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) return true;
        return false;
    }
}