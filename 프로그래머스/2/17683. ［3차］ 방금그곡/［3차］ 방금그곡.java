import java.util.*;

class Solution {
    
    static String answer = "";
    static List<Music> list;
    static class Music {
        int playTime, idx;
        String title;
        Music(int playTime, int idx, String title) {
            this.playTime = playTime;
            this.idx = idx;
            this.title = title;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        solve(m, musicinfos);
        return answer;
    }
    
    static void solve(String m, String[] musicinfos) {
        list = new ArrayList<>();
        
        String findM = replaceSheet(m);
        
        int idx = 0;
        
        for (String s : musicinfos) {
            String[] musicInfo = s.split(",");
            int playTime = calculatePlayTime(musicInfo[0], musicInfo[1]);
            String title = musicInfo[2];
            String sheet = musicInfo[3];
            String playingSheet = findPlayingSheet(playTime, replaceSheet(sheet));
            if (playingSheet.contains(findM)) {
                list.add(new Music(playTime, idx, title));
            }
            idx++;
        }
        
        sort();
        
        if (list.isEmpty()) {
            answer = "(None)";
        } else {
            answer = list.get(0).title;
        }
    }
    
    static void sort() {
        Collections.sort(list, (o1, o2) -> {
            if (o2.playTime == o1.playTime) return o1.idx - o2.idx;
            return o2.playTime - o1.playTime;
        });
    }
    
    static String findPlayingSheet(int playTime, String sheet) {
        String playingSheet = "";
        int idx = 0;
        int time = 0;
        
        while (idx++ < playTime) {
            if (time >= sheet.length()) time = 0;
            playingSheet += sheet.charAt(time++);
        }
        
        return playingSheet;
        
    }
    
    static int calculatePlayTime(String start, String end) {
        String[] startArr = start.split(":");
        String[] endArr = end.split(":");
        
        int startH = stringToInt(startArr[0]);
        int startM = stringToInt(startArr[1]);
        int endH = stringToInt(endArr[0]);
        int endM = stringToInt(endArr[1]);
        
        int result = 0;
        
        if (endM < startM) {
            result += (60 - startM) + endM;
            result += 60 * ((endH-1) - startH);
        } else {
            result += endM - startM;
            result += 60 * (endH - startH);
        }
        
        return result;
    }
    
    static String replaceSheet(String sheet) {
        String result = sheet;
        result = result.replaceAll("C#", "c");
        result = result.replaceAll("D#", "d");
        result = result.replaceAll("F#", "f");
        result = result.replaceAll("G#", "g");
        result = result.replaceAll("A#", "a");
        result = result.replaceAll("B#", "b");
        return result;
    }
    
    static int stringToInt(String s) {
        return Integer.parseInt(s);
    }
}