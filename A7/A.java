import java.io.*;
import java.util.*;

public class A{

    static ArrayList<int[]>[] g;
    static long[] dist;
    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] u = new int[M];
        int[] v = new int[M];
        int[] w = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) u[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) v[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) w[i] = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++)
            g[u[i]].add(new int[]{v[i], w[i]});

        dist = new long[N + 1];
        parent = new int[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dist[S] = 0;
        pq.add(new long[]{0, S});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int node = (int) cur[1];

            if (d != dist[node]) continue;

            for (int[] e : g[node]) {
                int to = e[0];
                long nd = d + e[1];

                if (nd < dist[to]) {
                    dist[to] = nd;
                    parent[to] = node;
                    pq.add(new long[]{nd, to});
                }
            }
        }

        if (dist[D] == Long.MAX_VALUE) {
            bw.write("-1");
            bw.flush();
            return;
        }

        bw.write(dist[D] + "\n");

        ArrayList<Integer> path = new ArrayList<>();
        for (int cur = D; cur != -1; cur = parent[cur])
            path.add(cur);

        Collections.reverse(path);
        for (int x : path) bw.write(x + " ");
        
        bw.write("\n");
        bw.flush();
    }
}
