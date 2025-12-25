import java.io.*;
import java.util.*;

public class C{

    static ArrayList<int[]>[] g;
    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w});
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d != dist[u]) continue;

            for (int[] e : g[u]) {
                int v = e[0];
                long w = e[1];
                long nd = Math.max(d, w);

                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new long[]{nd, v});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == INF) sb.append("-1 ");
            else sb.append(dist[i]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
