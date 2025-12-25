import java.io.*;
import java.util.*;

public class F {

    static final long INF = Long.MAX_VALUE / 4
;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w});
        }

        long[] dist1 = new long[N + 1];
        long[] dist2 = new long[N + 1];
        Arrays.fill(dist1, INF);
        Arrays.fill(dist2, INF);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dist1[S] = 0;
        pq.add(new long[]{0, S});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d > dist2[u]) continue;

            for (int[] e : g[u]) {
                int v = e[0];
                long nd = d + e[1];

                if (nd < dist1[v]) {
                    dist2[v] = dist1[v];
                    dist1[v] = nd;
                    pq.add(new long[]{nd, v});
                } else if (nd > dist1[v] && nd < dist2[v]) {
                    dist2[v] = nd;
                    pq.add(new long[]{nd, v});
                }
            }
        }

        System.out.println(dist2[D] == INF ? -1 : dist2[D]);
    }
}
