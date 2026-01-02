import java.io.*;
import java.util.*;

public class E {

    static class Task {
        long e, s;
        Task(long e, long s) {
            this.e = e;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Task[] tasks = new Task[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                long a = Long.parseLong(st.nextToken());
                long b = Long.parseLong(st.nextToken());

                long s = Math.min(a, b);
                long e = Math.max(a, b);

                tasks[i] = new Task(e, s); // store (end, start)
            }

            // sort by end time
            Arrays.sort(tasks, Comparator.comparingLong(t -> t.e));

            // TreeMap acts as a sorted multiset of end times
            TreeMap<Long, Integer> ends = new TreeMap<>();
            int used = 0;
            int done = 0;

            for (Task t : tasks) {
                // largest end < start
                Long key = ends.lowerKey(t.s);

                if (key != null) {
                    decrement(ends, key);
                    ends.merge(t.e, 1, Integer::sum);
                    done++;
                } else if (used < M) {
                    ends.merge(t.e, 1, Integer::sum);
                    used++;
                    done++;
                }
            }

            out.append(done).append('\n');
        }

        System.out.print(out.toString());
    }

    static void decrement(TreeMap<Long, Integer> map, long key) {
        int cnt = map.get(key);
        if (cnt == 1) map.remove(key);
        else map.put(key, cnt - 1);
    }
}
