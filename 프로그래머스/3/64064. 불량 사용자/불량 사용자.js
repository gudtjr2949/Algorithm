function solution(user_id, banned_id) {
    var answer = 0;
    
    const visited = Array(user_id.length).fill(false);
    const set = new Set();
    
    const regex = banned_id.map(id => new RegExp(`^${id.replaceAll('*', '.')}$`));

    
    const dfs = (idx = 0, input = []) => {
        if (idx === banned_id.length) {
            set.add(input.sort().join(','));
        } else {
            for (let i = 0; i < user_id.length; i++) {
                if (visited[i]) {
                    continue;
                }
                
                if (user_id[i].match(regex[idx])) {
                    visited[i] = true;
                    dfs(idx + 1, [...input, user_id[i]]);
                    visited[i] = false;
                }
            }
        }
    };
    
    dfs();
    
    answer = set.size;
    
    return answer;
}
