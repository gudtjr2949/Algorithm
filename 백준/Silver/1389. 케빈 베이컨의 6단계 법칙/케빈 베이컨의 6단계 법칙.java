import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //친구 관계 정보 그래프 형태로 저장할 리스트
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int N,min = Integer.MAX_VALUE, answer = -1;
    //BFS에 사용할 정보 클래스
    static class info{
        int index, count;
        public info(int index, int count){
            this.index = index;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        //그래프 초기화
        for(int i=0;i<=N;i++)
            graph.add(new ArrayList<>());
        //친구 관계 정보 그래프 형태로 저장
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            graph.get(B).add(A);
        }
        //각 인원 기준 BFS을 이용하여 케빈 베이컨 탐색 진행
        for(int i=1;i<=N;i++)
            bfs(i);
        //가장 작은 케빈 베이컨의 수를 가진 번호 BufferedWriter 저장
        bw.write(answer + "");	
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //BFS탐색으로 케빈 베이컨 수 탐색하는 함수
    static void bfs(int start){
        Queue<info> queue = new LinkedList<>();	//BFS에 사용할 Queue
        boolean[] visited = new boolean[N+1];	//사람 방문 확인 배열
        queue.add(new info(start, 0));	//시작 번호 저장
        visited[start] = true;	//시작 번호 방문 확인
        int result = 0;
        //BFS 탐색 진행
        while(!queue.isEmpty()){
            info cur = queue.poll();
            //인접한 관계 탐색
            for(int next : graph.get(cur.index)){
                if(!visited[next]){		//방문하지 않았던 관계일 때
                    result += cur.count + 1;	//현재 단계 더하기
                    visited[next] = true;
                    queue.add(new info(next, cur.count+1));
                }
            }
        }
        //케빈 베이컨 수 비교
        if(result < min){
            min = result;
            answer = start;
        }

    }
}