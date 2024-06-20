const path = process.platform === 'linux' ? '/dev/stdin' : 'example.txt';
const input = require('fs').readFileSync(path).toString().trim().split(/\r?\n/);
const N = +input.shift();

const dp = new Array(N+1);

dp[0] = 0;
dp[1] = 1;

for (let i = 2 ; i <= N ; i++) {
    dp[i] = BigInt(dp[i-2]) + BigInt(dp[i-1]);
}

console.log(String(dp[N]));