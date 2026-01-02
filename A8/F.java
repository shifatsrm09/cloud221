import java.io.*;
import java.util.*;

public class F {

    static class Task {
        long a, d;
        Task(long a, long d) {
            this.a = a;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long d = Long.parseLong(st.nextToken());
            tasks[i] = new Task(a, d);
        }

        // Sort by duration (Shortest Processing Time first)
        Arrays.sort(tasks, Comparator.comparingLong(t -> t.a));

        long currentTime = 0;
        long totalReward = 0;

        for (Task t : tasks) {
            currentTime += t.a;
            totalReward += t.d - currentTime;
        }

        System.out.println(totalReward);
    }
}
