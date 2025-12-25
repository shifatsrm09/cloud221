import java.io.*;
import java.util.*;

public class E {

    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] u = new int[M];
        int[] v = new int[M];
        int[] w = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) u[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) v[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) w[i] = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            g[u[i]].add(new int[]{v[i], w[i]});
        }

        long[][] dist = new long[N + 1][2];
        for (int i = 1; i <= N; i++)
            Arrays.fill(dist[i], INF);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // start node: allow both parities
        dist[1][0] = dist[1][1] = 0;
        pq.add(new long[]{0, 1, 0});
        pq.add(new long[]{0, 1, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int uNode = (int) cur[1];
            int p = (int) cur[2];

            if (d != dist[uNode][p]) continue;

            for (int[] e : g[uNode]) {
                int vNode = e[0];
                int np = e[1] & 1; // parity of edge

                if (np == p) continue; // same parity forbidden

                long nd = d + e[1];
                if (nd < dist[vNode][np]) {
                    dist[vNode][np] = nd;
                    pq.add(new long[]{nd, vNode, np});
                }
            }
        }

        long ans = Math.min(dist[N][0], dist[N][1]);
        System.out.println(ans == INF ? -1 : ans);
    }
}
