import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

// 백준 2800 : 괄호 제거
public class BOJ_2800 {
	
	static String s;
	static Stack<Integer> open;
	static ArrayList<Bracket> b = new ArrayList<>();
	static ArrayList<String> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        s = bf.readLine();
        
        open = new Stack<>();
        
        for (int i = 0 ; i < s.length() ; i++) {
        	if (s.charAt(i) == '(') {
        		open.add(i);
        	}
        	else if (s.charAt(i) == ')') {
        		b.add(new Bracket(open.pop(), i));
        	}
        }
        
        recursive(new boolean[b.size()], 0, 0);
        
        Collections.sort(list);
        
        print();
	}
	
	private static void recursive(boolean[] visited, int idx, int cur) {
		if (idx == b.size()) {
			char[] arr = new char[s.length()];
			
			arr = solve(visited);
			
			String tmp = "";
			
			for (int i = 0 ; i < arr.length ; i++) {
				if (arr[i] != ' ')
					tmp += arr[i];
			}
			
			if (!tmp.equals(s) && !list.contains(tmp))
				list.add(tmp);
			
			return;
		}
		
		for (int i = cur ; i < visited.length ; i++) {
			visited[idx] = true;
			recursive(visited, idx+1, i+1);
			visited[idx] = false;
			recursive(visited, idx+1, i+1);
		}
	}
	
	private static char[] solve(boolean[] visited) {
		char[] arr = new char[s.length()];
		
		for (int i = 0 ; i < s.length() ; i++)
			arr[i] = s.charAt(i);
		
		for (int i = 0 ; i < visited.length ; i++) {
			if (!visited[i]) {
				arr[b.get(i).open] = ' ';
				arr[b.get(i).close] = ' ';
			}
		}
		
		return arr;
	}
	
	private static void print() {
		for (int i = 0 ; i < list.size() ; i++)
			System.out.println(list.get(i));
	}
}

class Bracket {
	int open;
	int close;
	
	public Bracket() {}
	
	public Bracket(int open, int close) {
		this.open = open;
		this.close = close;
	}
}
