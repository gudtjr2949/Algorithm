import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] dp;
    static Book[] books;
    static class Book {
        int day, page;

        public Book(int day, int page) {
            this.day = day;
            this.page = page;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[M+1][N+1];
        books = new Book[M+1];

        for (int i = 1 ; i <= M ; i++) {
            st = new StringTokenizer(bf.readLine());
            books[i] = new Book(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        solve();

        System.out.println(dp[M][N]);
    }

    static void solve() {
        for (int i = 1 ; i <= M ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (j - books[i].day >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], books[i].page + dp[i-1][j - books[i].day]);
                }
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
            }
        }
    }
}