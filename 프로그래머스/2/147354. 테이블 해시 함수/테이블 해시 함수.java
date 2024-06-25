import java.util.*;

class Solution {
    
    static List<Node> list;
    static class Node {
        int[] arr;
        public Node(int[] arr) {
            this.arr = arr;
        }
        
        @Override
        public String toString() {
            String s = "";
            for (int i = 0 ; i < arr.length ; i++) {
                s += arr[i] + " ";
            }
            return s;
        }
    }
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        list = new ArrayList<>();
        
        for (int i = 0 ; i < data.length ; i++) {
            list.add(new Node(data[i]));
        }
        
        Collections.sort(list, new Comparator<Node>() {
           @Override
            public int compare(Node node1, Node node2) {
                if (node1.arr[col-1] == node2.arr[col-1])
                    return node2.arr[0] - node1.arr[0];
                return node1.arr[col-1] - node2.arr[col-1];
            }
        });
        
        int[] S = new int[row_end - row_begin + 1];
        
        int idx = 0;
        
        for (int i = row_begin-1 ; i < row_end ; i++) {
            int sum = 0;
            
            for (int j = 0 ; j < data[0].length ; j++) {
                sum += list.get(i).arr[j] % (i+1);
            }
            
            S[idx++] = sum;
        }
        
        for (int i = 0 ; i < S.length ; i++) {
            answer ^= S[i];
        }
        
        return answer;
    }
}