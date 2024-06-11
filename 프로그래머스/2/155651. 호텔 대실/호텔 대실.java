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
        
        list = new ArrayList<>();
        
        for (int i = 0 ; i < book_time.length ; i++) {
            int start = Integer.parseInt(book_time[i][0].replace(":", ""));
            int end = Integer.parseInt(book_time[i][1].replace(":", "")) + 10;
            
	        if (end % 100 >= 60){
	            end += 40;
	        }
            
            list.add(new Book(start, end));
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