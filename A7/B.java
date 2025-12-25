import java.io.*;
import java.util.*;

public class B {

    static ArrayList<int[]>[] g;
    static long INF = Long.MAX_VALUE / 4;

    static long[] dijkstra(int start, int N) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dist[start] = 0;
        pq.add(new long[]{0, start});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d != dist[u]) continue;

            for (int[] e : g[u]) {
                int v = e[0];
                long nd = d + e[1];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new long[]{nd, v});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v, w});
        }

        long[] distA = dijkstra(S, N);
        long[] distB = dijkstra(T, N);

        long bestTime = INF;
        int bestNode = -1;

        for (int i = 1; i <= N; i++) {
            if (distA[i] == INF || distB[i] == INF) continue;

            long meetTime = Math.max(distA[i], distB[i]);

            if (meetTime < bestTime ||
               (meetTime == bestTime && i < bestNode)) {
                bestTime = meetTime;
                bestNode = i;
            }
        }

        if (bestNode == -1) {
            System.out.println("-1");
        } else {
            System.out.println(bestTime + " " + bestNode);
        }
    }
}
