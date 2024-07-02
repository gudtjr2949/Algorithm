import java.util.*;

class Solution {
    
    static class Music {
        int idx, cnt;
        public Music(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
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
        Collections.sort(keyList, (s1, s2) -> num.get(s2) - (num.get(s1))); // 재생 횟수를 기준으로 내림차순 정렬
        
        for(String key : keyList) {
            List<Music> list = music.get(key);
             
            Collections.sort(list, (m1, m2) -> (m2.cnt - m1.cnt));
 
            answer.add(list.get(0).idx);
            if(list.size() > 1)
                answer.add(list.get(1).idx);
        }
 
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    
}