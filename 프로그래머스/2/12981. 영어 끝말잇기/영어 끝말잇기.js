function solution(n, words) {
    var answer = [];
    
    const set = new Set();
    
    let before = null;
    
    Loop : 
    for (let i = 0 ; i < words.length / n ; i++) {
        for (let j = 0 ; j < n ; j++) {
            let now = words[i*n + j];
            
            if (before === null) {
                set.add(now);
                before = now;
                continue;
            }
        
            if (!set.has(now) && before.charAt(before.length-1) === now.charAt(0) && now.length > 1) {
                before = now;
                set.add(now);
            } else { // 위의 조건 중, 하나라도 일치하지 않으면 끝말잇기 종료
                answer = [j+1, i+1];
                break Loop;
            }
        }
    }
    
    if (answer.length === 0) {
        answer = [0, 0];
    }
    

    return answer;
}