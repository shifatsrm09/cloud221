import java.io.*;
import java.util.*;

public class Main {

    // Edge representation
    static class Edge {
        int u, v;
        long w;

        Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // Disjoint Set Union (Union-Find)
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // path compression
            return parent[x];
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;

            if (rank[a] < rank[b]) {
                parent[a] = b;
            } else if (rank[a] > rank[b]) {
                parent[b] = a;
            } else {
                parent[b] = a;
                rank[a]++;
            }
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            String[] first = br.readLine().split(" ");
            int N = Integer.parseInt(first[0]);
            int M = Integer.parseInt(first[1]);

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                String[] p = br.readLine().split(" ");
                int u = Integer.parseInt(p[0]);
                int v = Integer.parseInt(p[1]);
                long w = Long.parseLong(p[2]);
                edges.add(new Edge(u, v, w));
            }

            // Sort edges by ASCENDING weight (Minimum Spanning Tree)
            edges.sort(Comparator.comparingLong(e -> e.w));

            DSU dsu = new DSU(N);
            long minWeight = 0;
            int usedEdges = 0;

            for (Edge e : edges) {
                if (dsu.union(e.u, e.v)) {
                    minWeight += e.w;
                    usedEdges++;
                    if (usedEdges == N - 1) break;
                }
            }

            out.append(minWeight).append('\n');
        }

        System.out.print(out.toString());
    }
}
