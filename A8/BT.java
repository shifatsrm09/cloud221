import java.io.*;
import java.util.*;

public class BT {

    static class Edge {
        int u, v;
        long w;

        Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static int[] parent, rank;

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) return false;

        if (rank[ra] < rank[rb]) {
            parent[ra] = rb;
        } else if (rank[ra] > rank[rb]) {
            parent[rb] = ra;
        } else {
            parent[rb] = ra;
            rank[ra]++;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Edge[] edges = new Edge[M];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long w = Long.parseLong(st.nextToken());
                edges[i] = new Edge(u, v, w);
            }

            Arrays.sort(edges, Comparator.comparingLong(e -> e.w));

            parent = new int[N + 1];
            rank = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            long mstCost = 0;
            int usedEdges = 0;

            for (Edge e : edges) {
                if (union(e.u, e.v)) {
                    mstCost += e.w;
                    usedEdges++;
                    if (usedEdges == N - 1) break;
                }
            }

            out.append(mstCost).append('\n');
        }

        System.out.print(out.toString());
    }
}
