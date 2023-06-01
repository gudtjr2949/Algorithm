import java.io.*;
import java.util.*;

// 백준 10994 : 별 찍기 - 19
public class BOJ_10994 {	
	public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// TODO Auto-generated method stub
        
        int N = Integer.parseInt(bf.readLine());
        
        int size = (N*4) - 3;
        
        int origin_size = size;
        
        char[][] board = new char[size][size];
        
        for (int i = 0 ; i < size ; i++) {
        	for (int j = 0 ; j < size ; j++)
        		board[i][j] = ' ';
        }
        
        star(board, origin_size, size, 0, 0);
        
        for (int i = 0 ; i < origin_size ; i++) {
        	for (int j = 0 ; j < origin_size ; j++)
        		System.out.print(board[i][j]);
        	System.out.println();
        }        
	}
	
	private static void star(char[][] board, int origin_size, int size, int x, int y) {
		if (size == 1) {
			board[origin_size/2][origin_size/2] = '*';
			return;
		}
		
		for (int i = y ; i < y + size ; i+=(size-1)) {
			for (int j = x ; j < x + size ; j++) {
				board[i][j] = '*';
				board[j][i] = '*';
			}
		}
		
		if (size != 1) {
			star(board, origin_size, size-4, x+2, y+2);
		}
	}
}
