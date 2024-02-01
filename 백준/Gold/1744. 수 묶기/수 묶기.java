import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int N;
    static ArrayList<Long> positiveArr, negativeArr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        positiveArr = new ArrayList<>();
        negativeArr = new ArrayList<>();

        long sum = 0;
        long one = 0;

        for (int i = 0 ; i < N ; i++) {
            long num = Long.parseLong(bf.readLine());
            sum += num;

            if (num > 1) {
                positiveArr.add(num);
            } else if (num <= 0){
                negativeArr.add(num);
            } else if (num == 1) {
                one++;
            }
        }

        Collections.sort(positiveArr);
        Collections.sort(negativeArr);

        System.out.println(Math.max(sum, solve() + one));
    }

    static long solve() {
        long sum = 0;

        for (int i = 0 ; i < negativeArr.size()-1 ; i+=2) {
            sum += negativeArr.get(i) * negativeArr.get(i+1);
        }

        // 만약에 negativeArr.size() 가 홀수라면 반드시 negativeArr.get(negativeArr.size()-1) 를 sum 에 더해주지 못함
        if (negativeArr.size() % 2 != 0) {
            sum += negativeArr.get(negativeArr.size()-1);
        }

        for (int i = positiveArr.size()-1 ; i >= 1 ; i-=2) {
            sum += positiveArr.get(i) * positiveArr.get(i-1);
        }

        // 만약에 positiveArr.size() 가 홀수라면 반드시 positiveArr.get(0) 을 sum 에 더해주지 못함
        if (positiveArr.size() % 2 != 0) {
            sum += positiveArr.get(0);
        }

        return sum;
    }
}