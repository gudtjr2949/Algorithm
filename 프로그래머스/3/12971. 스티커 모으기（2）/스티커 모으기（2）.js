function solution(sticker) {
    var answer = 0;
    let N = sticker.length;
    
    if (N === 1) return sticker[0];
    
    let dp = [];
    dp[0] = sticker[0];
    dp[1] = sticker[0];
    
    for (let i = 2 ; i < N-1; i++) {
        dp[i] = Math.max(dp[i-1], dp[i-2]+sticker[i]);
    }
    
    for (let i = 0 ; i < dp.length; i++) {
        answer = Math.max(answer, dp[i]);
    }
    
    dp = [];
    dp[0] = 0;
    dp[1] = sticker[1];
    
    for (let i = 2 ; i < N; i++) {
        dp[i] = Math.max(dp[i-1], dp[i-2]+sticker[i]);
    }
    
    for (let i = 0 ; i < dp.length; i++) {
        answer = Math.max(answer, dp[i]);
    }

    return answer;
}