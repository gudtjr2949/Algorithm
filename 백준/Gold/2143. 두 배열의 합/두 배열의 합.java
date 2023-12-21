import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long T, answer;
    static long[] sumA, sumB;
    static int N, M, lenA, lenB;
    static long[] prefixA, prefixB;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        answer = 0;

        T = Long.parseLong(bf.readLine());

        N = Integer.parseInt(bf.readLine());

        long[] A = new long[N];
        prefixA = new long[N+1];
        prefixA[0] = 0;
        lenA = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            A[i] = Long.parseLong(st.nextToken());
            prefixA[i+1] = prefixA[i] + A[i];
            lenA += i+1;
        }

        sumA = new long[lenA];

        M = Integer.parseInt(bf.readLine());

        long[] B = new long[M];
        prefixB = new long[M+1];
        prefixB[0] = 0;
        lenB = 0;

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            B[i] = Long.parseLong(st.nextToken());
            prefixB[i+1] = prefixB[i] + B[i];
            lenB += i+1;
        }

        sumB = new long[lenB];

        getSumArr();

        getCount();

        System.out.println(answer);
    }

    // A와 B에서 구할 수 있는 부분 합 배열 만들기
    static void getSumArr() {
        int idx = 0;
        // A배열
        for (int i = 1 ; i < N+1 ; i++) {
            for (int j = i ; j < N+1 ; j++) {
                sumA[idx] = prefixA[j] - prefixA[i-1];
                idx++;
            }
        }

        Arrays.sort(sumA);

        idx = 0;
        // B배열
        for (int i = 1 ; i < M+1 ; i++) {
            for (int j = i ; j < M+1 ; j++) {
                sumB[idx] = prefixB[j] - prefixB[i-1];
                idx++;
            }
        }

        Arrays.sort(sumB);
    }

    static void getCount() {
        int start = 0;
        int end = sumB.length - 1;

        while (start < sumA.length && end >= 0) {
            long sum = sumA[start] + sumB[end];

            if (sum == T) {
                // sumA 과, sumB 에 똑같은 수가 있는지 찾아야 함
                long a = sumA[start];
                long cntA = 0;
                while (start < sumA.length && sumA[start] == a) {
                    start++;
                    cntA++;
                }

                long b = sumB[end];
                long cntB = 0;
                while (end >= 0 && sumB[end] == b) {
                    end--;
                    cntB++;
                }

                answer += cntA * cntB;
            } else if (sum > T) {
                end--;
            } else {
                start++;
            }
        }
    }
}