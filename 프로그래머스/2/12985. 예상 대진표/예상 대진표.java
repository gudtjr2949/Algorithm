class Solution {
    public int solution(int n, int a, int b) {
        int answer = 0;
        
        while (true) {
            a = a/2 + a%2; // 앞에 있는 팀 절반은 탈락 -> a/2, 만약 a/2가 홀수라면 + a%2
            b = b/2 + b%2; // 앞에 있는 팀 절반은 탈락 -> b/2, 만약 b/2가 홀수라면 + b%2
            answer++;
            if (a == b) break;
        }

        return answer;
    }

}