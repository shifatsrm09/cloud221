import java.io.*;
import java.util.*;

public class DT {

    static class Task {
        long s, e;
        Task(long s, long e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine().trim());
            Task[] tasks = new Task[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long s = Long.parseLong(st.nextToken());
                long e = Long.parseLong(st.nextToken());
                tasks[i] = new Task(s, e);
            }

            // Sort by end time, then start time
            Arrays.sort(tasks, (a, b) -> {
                if (a.e != b.e) return Long.compare(a.e, b.e);
                return Long.compare(a.s, b.s);
            });

            List<Task> chosen = new ArrayList<>();
            long lastEnd = -1;

            for (Task t : tasks) {
                if (t.s > lastEnd) {   // STRICT overlap rule
                    chosen.add(t);
                    lastEnd = t.e;
                }
            }

            output.append(chosen.size()).append('\n');
            for (Task t : chosen) {
                output.append(t.s).append(' ').append(t.e).append('\n');
            }
        }

        System.out.print(output.toString());
    }
}
