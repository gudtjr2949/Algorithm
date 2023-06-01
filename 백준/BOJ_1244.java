package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1244 : 스위치 켜고 끄기
public class BOJ_1244 {

    static int N; // 스위치 개수
    static int[] arr;
    static int Student_N; // 학생 수
    static Student[] student;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        arr = new int[N];


        String s = bf.readLine();
        String[] s_arr = s.split(" ");
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(s_arr[i]);
        }

        Student_N = Integer.parseInt(bf.readLine());
        student = new Student[Student_N];

        for (int i = 0 ; i < Student_N ; i++) {
            String s2 = bf.readLine();
            String[] s2_arr = s2.split(" ");
            student[i] = new Student(Integer.parseInt(s2_arr[0]), Integer.parseInt(s2_arr[1]));
        }

        solve();

        print();
    }

    private static void solve() {
        for (int i = 0 ; i < Student_N ; i++) {
            if (student[i].gender == 1) { // 남자
                for (int j = student[i].number-1 ; j < N ; j += student[i].number) {
                    if (arr[j] == 1)
                        arr[j] = 0;
                    else
                        arr[j] = 1;
                }
            }
            else { // 여자
                for (int j = 0 ; j < N ; j++) {
                    int left = (student[i].number-1) + (j * -1);
                    int right = (student[i].number-1) + (j * 1);

                    if (left < 0 || right >= N){
                        break;
                    }

                    if (arr[left] == arr[right]) {
                        if (arr[left] == 1) {
                            arr[left] = 0;
                            arr[right] = 0;
                        }
                        else {
                            arr[left] = 1;
                            arr[right] = 1;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }

    private static void print() {
        for (int i = 0 ; i < N ; i++) {
            if (i % 20 == 0 && i != 0) {
                System.out.println();
            }
            System.out.print(arr[i] + " ");
        }
    }

    static class Student {
        int gender;
        int number;

        public Student(int gender, int number) {
            this.gender = gender;
            this.number = number;
        }
    }
}
