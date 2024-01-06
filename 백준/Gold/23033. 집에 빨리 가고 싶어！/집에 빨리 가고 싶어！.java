import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] dist;
	static List<List<Subway>> list;

	static class Subway implements Comparable<Subway> {
		private int station, time, waiting, nowTime;

		public Subway(int station, int time, int waiting, int nowTime) {
			this.station = station;
			this.time = time;
			this.waiting = waiting;
			this.nowTime = nowTime;
		}

		@Override
		public int compareTo(Subway s) {
			return this.nowTime - s.nowTime;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		for (int i = 0 ; i <= N ; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(bf.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			list.get(A).add(new Subway(B, T, W, 0));
		}

		Dijkstra();

		System.out.println(dist[N]);
	}

	private static void Dijkstra() {
		dist = new int[N+1];

		for (int i = 0 ; i <= N ; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[1] = 0;

		Queue<Subway> Q = new PriorityQueue<>();

		Q.add(new Subway(1, 0, 0, 0));

		while (!Q.isEmpty()) {
			Subway currentSubway = Q.poll();

			if (currentSubway.station == N) {
				break;
			}

			for (int i = 0 ; i < list.get(currentSubway.station).size() ; i++) {
				Subway nextSubway = list.get(currentSubway.station).get(i);

				int nextTime = 0;

				// 대기시간이 없는 경우
				if (currentSubway.nowTime % nextSubway.waiting == 0) {
					nextTime = currentSubway.nowTime + nextSubway.time;
				} else {
					for (int j = 1 ; j <= 10 ; j++) {
						if ((currentSubway.nowTime + j) % nextSubway.waiting == 0) {
							nextTime = (currentSubway.nowTime + j) + nextSubway.time;
							break;
						}
					}
				}

				if (nextTime < dist[nextSubway.station]) {
					dist[nextSubway.station] = nextTime;
					Q.add(new Subway(nextSubway.station, nextSubway.time, nextSubway.waiting, dist[nextSubway.station]));
				}
			}
		}

		// System.out.println(Arrays.toString(dist));
	}
}