import java.util.*;
import java.io.*;

// 백준 1485 : 정사각형
public class BOJ_1485 {

    /*
    * 조건 1 : 점은 x좌표, y좌표 4개씩 입력받음
    * 조건 2 : 정사각형의 조건은 모든 점 사이의 거리의 갯수가 2개여야 함. (가로길이 == 세로길이, 점 사이의 대각선 길이)
    * 조건 3 : 모든 점 탐색 */

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++) {
            ArrayList<Point> list = new ArrayList<>();
            Set<Double> distance = new HashSet<>();

            for (int j = 0 ; j < 4 ; j++) { // 조건 1
                String s = bf.readLine();
                String[] s_arr = s.split(" ");

                list.add(new Point(Integer.parseInt(s_arr[0]), Integer.parseInt(s_arr[1])));
            }

            // 조건 2
            for (int j = 0 ; j < 4 ; j++){
                for (int q = 1 ; q < 4 ; q++){
                    distance.add(dis_point(list.get(0), list.get(q)));
                }
                if (distance.size() > 2) {
                    break;
                }

                // 조건 3
                Point tmp = list.get(0);
                list.remove(0);
                list.add(tmp);
            }

            // 조건 2
            if (distance.size() == 2)
                sb.append(1).append("\n");
            else
                sb.append(0).append("\n");
        }

        System.out.println(sb);
    }

    static public double dis_point(Point p1, Point p2){
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
