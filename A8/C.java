import java.io.*;
import java.util.*;

public class C {

    static class Edge {
        int u, v;
        long w;
        boolean used;
        Edge(int u, int v, long w) {
            this.u = u; this.v = v; this.w = w;
        }
    }

    static class DSU {
        int[] p, r;
        DSU(int n) {
            p = new int[n+1];
            r = new int[n+1];
            for (int i = 1; i <= n; i++) p[i] = i;
        }
        int find(int x) {
            if (p[x] != x) p[x] = find(p[x]);
            return p[x];
        }
        boolean union(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return false;
            if (r[a] < r[b]) p[a] = b;
            else if (r[a] > r[b]) p[b] = a;
            else { p[b] = a; r[a]++; }
            return true;
        }
    }

    static int N, M, LOG;
    static List<long[]>[] tree;
    static int[][] parent;
    static long[][] max1, max2;
    static int[] depth;

    static void dfs(int u, int p, long w) {
        parent[0][u] = p;
        max1[0][u] = w;
        max2[0][u] = -1;
        for (long[] e : tree[u]) {
            int v = (int)e[0];
            long wt = e[1];
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            dfs(v, u, wt);
        }
    }

    static long query(int u, int v, long w) {
        long a = -1, b = -1;

        if (depth[u] < depth[v]) {
            int t = u; u = v; v = t;
        }

        for (int i = LOG - 1; i >= 0; i--) {
            if (parent[i][u] != 0 && depth[parent[i][u]] >= depth[v]) {
                long x = max1[i][u], y = max2[i][u];
                if (x > a) { b = a; a = x; }
                else if (x > b && x != a) b = x;
                if (y > b && y != a) b = y;
                u = parent[i][u];
            }
        }

        if (u != v) {
            for (int i = LOG - 1; i >= 0; i--) {
                if (parent[i][u] != parent[i][v]) {
                    long[] xs = {max1[i][u], max2[i][u], max1[i][v], max2[i][v]};
                    for (long x : xs) {
                        if (x > a) { b = a; a = x; }
                        else if (x > b && x != a) b = x;
                    }
                    u = parent[i][u];
                    v = parent[i][v];
                }
            }
            long[] xs = {max1[0][u], max1[0][v]};
            for (long x : xs) {
                if (x > a) { b = a; a = x; }
                else if (x > b && x != a) b = x;
            }
        }

        if (w > a) return a;
        if (w > b) return b;
        return -1;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Long.parseLong(st.nextToken())
            );
        }

        Arrays.sort(edges, Comparator.comparingLong(e -> e.w));
        DSU dsu = new DSU(N);

        tree = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        long mst = 0;
        int used = 0;
        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                e.used = true;
                mst += e.w;
                used++;
                tree[e.u].add(new long[]{e.v, e.w});
                tree[e.v].add(new long[]{e.u, e.w});
            }
        }

        if (used != N - 1) {
            System.out.println(-1);
            return;
        }

        LOG = 1;
        while ((1 << LOG) <= N) LOG++;
        parent = new int[LOG][N+1];
        max1 = new long[LOG][N+1];
        max2 = new long[LOG][N+1];
        depth = new int[N+1];

        dfs(1, 0, -1);

        for (int i = 1; i < LOG; i++) {
            for (int v = 1; v <= N; v++) {
                int p = parent[i-1][v];
                parent[i][v] = parent[i-1][p];

                long[] arr = {
                    max1[i-1][v], max2[i-1][v],
                    max1[i-1][p], max2[i-1][p]
                };
                long a = -1, b = -1;
                for (long x : arr) {
                    if (x > a) { b = a; a = x; }
                    else if (x > b && x != a) b = x;
                }
                max1[i][v] = a;
                max2[i][v] = b;
            }
        }

        long ans = Long.MAX_VALUE;
        for (Edge e : edges) {
            if (!e.used) {
                long rem = query(e.u, e.v, e.w);
                if (rem != -1) {
                    ans = Math.min(ans, mst + e.w - rem);
                }
            }
        }

        System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
    }
}
