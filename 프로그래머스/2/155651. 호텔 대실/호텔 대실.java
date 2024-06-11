import java.util.*;

class Solution {
    
    static int answer;
    static List<Book> list;
    static class Book {
        int start, end;
        
        public Book(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public String toString() {
            return start + " " + end;
        }
    }
    
    public int solution(String[][] book_time) {
        answer = 0;
        
        Arrays.sort(book_time, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				if (o1[0].equals(o2[0]))
					return o1[1].compareTo(o2[1]);
				else
					return o1[0].compareTo(o2[0]);
			}
		});
        
        // 예약 시간 정수로 변경
		int[][] time = new int[book_time.length][2];
		for (int i = 0; i < book_time.length; i++) {
				int start_time = Integer.parseInt(book_time[i][0].replace(":", ""));
				int end_time = Integer.parseInt(book_time[i][1].replace(":", ""));
				
				end_time += 10;
	            if(end_time%100 >= 60){
	                end_time+=40;
	            }
	            
	            time[i][0] = start_time;
	            time[i][1] = end_time;
		}
        
        list = new ArrayList<>();
        
        for (int i = 0 ; i < book_time.length ; i++) {
            list.add(new Book(time[i][0], time[i][1]));
        }
        
        Collections.sort(list, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                if (b1.start == b2.start) 
                    return b2.end - b1.end;
                else 
                    return b1.start - b2.start;
            }
        });
                        
        solve();
        
        return answer;
    }
    
    static void solve() {
        PriorityQueue<Book> PQ = new PriorityQueue<> ((b1, b2) -> b1.end - b2.end);
        
        answer++;
        PQ.add(new Book(list.get(0).start, list.get(0).end));
        
        for (int i = 1 ; i < list.size() ; i++) {
            Book now = list.get(i);
            
            if (PQ.peek().end <= now.start) {
                PQ.poll();
            } else {
                answer++;
            }
            
            PQ.add(now);
        }
    }
}