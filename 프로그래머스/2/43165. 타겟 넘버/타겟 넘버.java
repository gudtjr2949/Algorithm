class Solution {
    static int answer = 0;
    static int[] arr;

    public int solution(int[] numbers, int target) {
        arr = new int[numbers.length];
        
        for (int i = 0 ; i < numbers.length ; i++) arr[i] = numbers[i];
        
        dfs(0, target, 0);

        return answer;
    }

    public void dfs(int idx, int target, int sum){
        if(idx == arr.length){
            if(target == sum) answer++;
        } else {
            dfs(idx + 1, target, sum + arr[idx]);
            dfs(idx + 1, target, sum - arr[idx]);
        }
    }
}