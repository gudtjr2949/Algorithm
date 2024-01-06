import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		long G = Long.parseLong(bf.readLine());

		long now = 2;
		long memory = 1;
		List<Long> list = new ArrayList<>();

		while (now <= 100000) {
			long result = (now * now) - (memory * memory);

			if (result == G) {
				list.add(now);
			}

			if (result <= G) {
				now++;
			} else {
				memory++;
			}
		}

		Collections.sort(list);

		if (list.size() == 0)
			System.out.println(-1);
		else {
			for (int i = 0 ; i < list.size() ; i++)
				System.out.println(list.get(i));
		}
	}
}