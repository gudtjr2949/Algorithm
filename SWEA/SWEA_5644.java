package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// SWEA 5644 : 무선 충전
public class SWEA_5644 {

    static int M;
    static int A;
    static int[][] map;
    static int[] move_A;
    static int[] move_B;
    static Charger[] chargers;
    static int answer = 0;

    static int[] nx = { 0, 0, 1, 0, -1 };
    static int[] ny = { 0, -1, 0, 1, 0 };

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            String ma = bf.readLine();
            String[] ma_arr = ma.split(" ");
            M = Integer.parseInt(ma_arr[0]);
            A = Integer.parseInt(ma_arr[1]);
            chargers = new Charger[A];
            map = new int[10][10];
            move_A = new int[M];
            move_B = new int[M];
            String s = bf.readLine();
            String s2 = bf.readLine();
            String[] s_arr = s.split(" ");
            String[] s2_arr = s2.split(" ");
            answer = 0;

            for (int j = 0; j < M; j++) {
                move_A[j] = Integer.parseInt(s_arr[j]);
                move_B[j] = Integer.parseInt(s2_arr[j]);
            }

            for (int j = 0; j < A; j++) {
                String a = bf.readLine();
                String[] a_arr = a.split(" ");
                chargers[j] = new Charger(Integer.parseInt(a_arr[0])-1, Integer.parseInt(a_arr[1])-1,
                        Integer.parseInt(a_arr[2]), Integer.parseInt(a_arr[3]));
            }

            solve();

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve() {
        Person A = new Person(0, 0);
        Person B = new Person(9, 9);

        for (int i = 0; i <= M; i++) {
            // 1. 겹침 && 충전소가 같음 :
            if ((cross_Charger(A) || cross_Charger(B)) && same_location(A, B)) {
                answer += find_max(A, B);
            }
            // 2. 겹침 : 겹치기만 한다면 그냥 겹쳐져있는 충전소 중에서 더 큰 값 고르면 됨
            else if (cross_Charger(A) || cross_Charger(B)) {
                answer += Sum_Charger(A);
                answer += Sum_Charger(B);
            }
            // 3. 충전소가 같음 : 충전소가 같기만 한다면 그냥 해당 충전소 P값 나누기 2 해주면 됨
            else if (same_location(A, B)) {
                answer += Sum_Charger(A);
            } else {
                answer += Sum_Charger(A);
                answer += Sum_Charger(B);
            }

//          System.out.println(find_max(A, B));
//          System.out.println(i + " " + answer);

            if (i != M) {
                // A 이동
                A.x = A.x + nx[move_A[i]];
                A.y = A.y + ny[move_A[i]];

                // B 이동
                B.x = B.x + nx[move_B[i]];
                B.y = B.y + ny[move_B[i]];
            }
        }
    }

    private static int Sum_Charger(Person p) {
        int sum = 0;

        for (int i = 0; i < A; i++) {
            if (Math.abs(p.x - chargers[i].x) + Math.abs(p.y - chargers[i].y) <= chargers[i].c) {
                sum = Math.max(sum, chargers[i].p);
            }
        }

        return sum;
    }

    // 같은 위치에 있는지 확인
    private static boolean same_location(Person a, Person b) {
        for (int i = 0; i < A; i++) {
            if (Math.abs(a.x - chargers[i].x) + Math.abs(a.y - chargers[i].y) <= chargers[i].c
                    && Math.abs(b.x - chargers[i].x) + Math.abs(b.y - chargers[i].y) <= chargers[i].c) {
                return true;
            }
        }
        return false;
    }

    private static int find_max(Person a, Person b) {
        ArrayList<Charger> possible_A = new ArrayList<>();
        ArrayList<Charger> possible_B = new ArrayList<>();

        int max = 0;

        for (int i = 0; i < A; i++) {
            if (Math.abs(a.x - chargers[i].x) + Math.abs(a.y - chargers[i].y) <= chargers[i].c) {
                possible_A.add(chargers[i]);
            }

            if (Math.abs(b.x - chargers[i].x) + Math.abs(b.y - chargers[i].y) <= chargers[i].c) {
                possible_B.add(chargers[i]);
            }
        }

        for (int i = 0 ; i < possible_A.size() ; i++) {
            Charger a_Charger = possible_A.get(i);
            for (int j = 0 ; j < possible_B.size() ; j++) {
                Charger b_Charger = possible_B.get(j);

                if (a_Charger == b_Charger) {
                    max = Math.max(max, a_Charger.p);
                }
                else {
                    max = Math.max(max, a_Charger.p + b_Charger.p);
                }
            }
        }

        return max;
    }


    // 겹쳐있는지 확인
    private static boolean cross_Charger(Person p) {
        int cnt = 0;
        for (int i = 0; i < A; i++) {
            if (Math.abs(p.x - chargers[i].x) + Math.abs(p.y - chargers[i].y) <= chargers[i].c) {
                cnt++;
            }
        }

        if (cnt > 1) {
            return true;
        } else {
            return false;
        }
    }

    static class Charger {
        int x;
        int y;
        int c;
        int p;

        public Charger(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

    static class Person {
        int x;
        int y;

        public Person(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }
}
