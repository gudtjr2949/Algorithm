// 입력값 파싱
const path = process.platform === 'linux' ? '/dev/stdin' : 'example.txt';
const input = require('fs').readFileSync(path).toString().trim().split(/\r?\n/);
const N = +input.shift();

// 수열 A의 각항 합을 담은 2차원 배열 S 구하기
const S = Array.from(new Array(N), () => new Array(N).fill(0));

input.forEach((val, i) => {
    const row = val.split(' ').map(Number);
    row.forEach((v, j) => {
        S[i][j] = v;
    });
});

// 수열 A 구하기
let A = new Array(N);
if (N === 2) A = [1, 1];
else {
    let first = findFirst();
    A[0] = first;
    for (let i = 1 ; i < N ; i++) {
        A[i] = S[0][i] - first;
    }
}

console.log(A.join(' '));

function findFirst() {
    let sum = 0;
    for (let i = 1 ; i < N ; i++) {
        sum += S[0][i];
    }

    let a = 1;
    let b = 2;

    while (a < N && b < N) {
        sum -= S[a][b];
        a += 2;
        b += 2;
    }

    let result = 0;

    if (N % 2 !== 0) {
        result = sum / (N-1);
    } else {
        sum -= S[0][N-1];
        result = sum / (N-2);
    }

    return result;
}