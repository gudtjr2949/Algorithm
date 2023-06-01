import java.io.*;
import java.util.*;

// 백준 1592 : 영식이와 친구들
public class BOJ_1592 {

    /* 조건 1 : 한 사람이 공을 M번 받으면 게임 종료
    * 조건 2 : 현재 공을 받은 횟수(Person.ball_cnt)가 홀수이면 poll & add
    * 조건 3 : 현재 공을 받은 횟수(Person.ball_cnt)가 짝수이면 pollLast & addFirst */

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        int N = Integer.parseInt(s_arr[0]);
        int M = Integer.parseInt(s_arr[1]);
        int L = Integer.parseInt(s_arr[2]);

        Deque<Person> deque = new ArrayDeque<>();

        deque.add(new Person(1,1));

        for (int i = 1 ; i < N ; i++){
            deque.add(new Person(i, 0));
        }

        int cnt = 0;

        Loop:
        while (deque.peek().ball_cnt != M) { // 조건 1
            if (deque.peek().ball_cnt % 2 == 1){ // 조건 2
                for (int i = 0 ; i < L ; i++){
                    Person p = deque.poll();
                    deque.add(p);
                }
            }

            else { // 조건 3
                for (int i = 0 ; i < L ; i++){
                    Person p = deque.pollLast();
                    deque.addFirst(p);
                }
            }

            cnt++;
            deque.peek().ball_cnt++;
        }

        System.out.println(cnt);
    }
}

class Person {
    int number;
    int ball_cnt;

    Person(int number, int ball_cnt){
        this.number = number;
        this.ball_cnt = ball_cnt;
    }
}
