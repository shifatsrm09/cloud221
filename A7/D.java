import java.io.*;
import java.util.*;

public class D {

    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        long[] weight = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weight[i] = Long.parseLong(st.nextToken());
        }

        ArrayList<Integer>[] g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[S] = weight[S];

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{dist[S], S});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d != dist[u]) continue;

            for (int v : g[u]) {
                long nd = d + weight[v];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new long[]{nd, v});
                }
            }
        }

        if (dist[D] == INF) {
            System.out.println("-1");
        } else {
            System.out.println(dist[D]);
        }
    }
}
