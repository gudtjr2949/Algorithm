package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 19942 : 다이어트
public class BOJ_19942 {

    static int N;
    static Ingredient[] ing; // 모든 재료
    static Ingredient min_ing; // 최소 영양소
    static int answer = Integer.MAX_VALUE;
    static String answer_ing;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        ing = new Ingredient[N];
        String s1 = bf.readLine();
        String[] s1_arr = s1.split(" ");

        min_ing = new Ingredient(Integer.parseInt(s1_arr[0]), Integer.parseInt(s1_arr[1]), Integer.parseInt(s1_arr[2]), Integer.parseInt(s1_arr[3]));

        for (int i = 0; i < N; i++) {
            String s2 = bf.readLine();
            String[] s2_arr = s2.split(" ");
            ing[i] = new Ingredient(Integer.parseInt(s2_arr[0]), Integer.parseInt(s2_arr[1]), Integer.parseInt(s2_arr[2]), Integer.parseInt(s2_arr[3]), Integer.parseInt(s2_arr[4]));
        }

        dfs(0, 0, new int[N]);

        if (answer != Integer.MAX_VALUE) {
            System.out.println(answer);
            System.out.println(answer_ing);
        } else {
            System.out.println(-1);
        }
    }

    private static void dfs(int idx, int cur, int[] input) {
        if (check(idx, input)) {
            return;
        }

        if (idx == input.length) {
            return;
        }

        for (int i = cur; i < N; i++) {
            input[idx] = i;
            dfs(idx + 1, i + 1, input);
        }
    }

    private static boolean check(int idx, int[] input) {
        int protein = 0;
        int fat = 0;
        int carbo = 0;
        int vitamin = 0;
        int price = 0;

        for (int i = 0; i < idx; i++) {
            protein += ing[input[i]].protein;
            fat += ing[input[i]].fat;
            carbo += ing[input[i]].carbo;
            vitamin += ing[input[i]].vitamin;
            price += ing[input[i]].price;
        }

        if (protein >= min_ing.protein && fat >= min_ing.fat && carbo >= min_ing.carbo && vitamin >= min_ing.vitamin) {
            if (answer > price) {
                answer = price;

                answer_ing = "";
                for (int i = 0; i < idx; i++) {
                    answer_ing += (input[i] + 1) + " ";
                }

            }
            return true;
        }

        return false;
    }

    static class Ingredient {
        int protein;
        int fat;
        int carbo;
        int vitamin;
        int price;

        public Ingredient(int protein, int fat, int carbo, int vitamin) {
            this.protein = protein;
            this.fat = fat;
            this.carbo = carbo;
            this.vitamin = vitamin;
        }

        public Ingredient(int protein, int fat, int carbo, int vitamin, int price) {
            super();
            this.protein = protein;
            this.fat = fat;
            this.carbo = carbo;
            this.vitamin = vitamin;
            this.price = price;
        }
    }
}
