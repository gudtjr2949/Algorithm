import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Person implements Comparable<Person> {
        int first, second;

        public Person(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Person p) {
            return this.first - p.first;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test = 0 ; test < T ; test++) {
            int N = Integer.parseInt(bf.readLine());

            int cnt = N;

            Person[] p = new Person[N];

            for (int i = 0 ; i < N ; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                p[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            Arrays.sort(p);

            int recentSecond = p[0].second; // 서류 1등의 면접등수

            for (int i = 1 ; i < N ; i++) {
                if (p[i].second > recentSecond) {
                    cnt--;
                } else {
                    recentSecond = p[i].second;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}