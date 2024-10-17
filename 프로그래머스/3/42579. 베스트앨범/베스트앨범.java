import java.util.*;

class Solution {
    
    static class Music {
        int idx, cnt;
        String genre;
        
        public Music(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
        
        @Override
        public String toString() {
            return String.format("음악 (인덱스 = %d, 횟수 = %d)", idx, idx);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        
        Map<String, Integer> num = new HashMap<>(); // 장르별 재생횟수
        Map<String, List<Music>> music = new HashMap<>(); // 장르별 음악 인덱스, 재생 횟수
        
        for(int i = 0; i < plays.length; i++) {
            if(!num.containsKey(genres[i])) {
                List<Music> list = new ArrayList<>();
                list.add(new Music(i, plays[i]));
                music.put(genres[i], list);
                num.put(genres[i], plays[i]);
            } else {
                music.get(genres[i]).add(new Music(i, plays[i]));
                num.put(genres[i], num.get(genres[i]) + plays[i]);
            }
        }
        
        List<String> keyList = new ArrayList(num.keySet()); // 장르가 하나씩 리스트에 들어감
        Collections.sort(keyList, (o1, o2) -> num.get(o2) - num.get(o1));
        
        for (String genre : keyList) {
            Collections.sort(music.get(genre), (o1, o2) -> {
                if (o2.cnt == o1.cnt) return o1.idx - o2.idx;
                else return o2.cnt - o1.cnt;
            });
            
            if (music.get(genre).size() == 1) {
                answer.add(music.get(genre).get(0).idx);
                continue;
            }
            
            for (int i = 0 ; i < 2 ; i++) {
                answer.add(music.get(genre).get(i).idx);
            }
        }
        
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}