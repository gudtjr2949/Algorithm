import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int S, answer = 0;
    static boolean[][] visited;
    static class Emoji {
        int screen, clipboard, time;

        public Emoji(int screen, int clipboard, int time) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(bf.readLine());
        visited = new boolean[10000][10000];
        bfs();
        System.out.println(answer);
    }

    static void bfs() {
        Queue<Emoji> Q = new LinkedList<>();
        Q.add(new Emoji(1, 0, 0));

        while (!Q.isEmpty()) {
            Emoji e = Q.poll();

            if ((e.screen == 0 && e.clipboard == 0) || (e.screen < 0 || e.clipboard < 0)) continue;

            if (e.screen == S) {
                answer = e.time;
                return;
            }

            // 1. 화면에 있는 이모티콘을 클립보드에 복사하는 경우
            if (e.screen > 0 && e.screen != e.clipboard && !visited[e.screen][e.screen]) {
                visited[e.screen][e.screen] = true;
                Q.add(new Emoji(e.screen, e.screen, e.time+1));
            }
            // 2. 클립보드에 있는 이모티콘을 화면에 붙여넣기하는 경우
            if (e.clipboard > 0 && !visited[e.screen + e.clipboard][e.clipboard]) {
                visited[e.screen + e.clipboard][e.clipboard] = true;
                Q.add(new Emoji(e.screen + e.clipboard, e.clipboard, e.time+1));
            }
            // 3. 화면에 있는 이모티콘 하나를 삭제하는 경우
            if (e.screen > 0 && !visited[e.screen-1][e.clipboard]) {
                visited[e.screen-1][e.clipboard] = true;
                Q.add(new Emoji(e.screen-1, e.clipboard, e.time+1));
            }
        }
    }
}