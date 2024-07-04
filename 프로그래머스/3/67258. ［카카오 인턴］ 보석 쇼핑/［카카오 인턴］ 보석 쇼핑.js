function solution(gems) {
    var answer = [];
    
    const set = new Set(gems);
    
    let allGems = set.size;
    
    const map = new Map();
    
    map.set(gems[0], 1);
    
    let left = 0;
    let right = 0;
    
    answer[0] = 0;
    answer[1] = gems.length;
    
    while (left < gems.length && right < gems.length) {        
        if (map.size === allGems) {
            // 최소 거리 체크
            if (answer[1] - answer[0] > right - left) {
                answer[0] = left+1;
                answer[1] = right+1;
            }
            
            map.set(gems[left], map.get(gems[left]) - 1);
            
            if (map.get(gems[left]) === 0) {
                map.delete(gems[left]);
            } 
            
            left++;
        } else {
            right++;
            
            if (right === gems.length) break;
            
            if (!map.has(gems[right])) {
                map.set(gems[right], 1);
            } else {
                map.set(gems[right], map.get(gems[right])+1);
            }
        }
    }
    
    return answer;
}