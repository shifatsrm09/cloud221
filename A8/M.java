import java.io.*;
import java.util.*;

public class MaximumSpanningTree {

    // Union-Find Data Structure
    static class UnionFind {
        int[] parent, rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;  // Each node is its own parent initially
            }
        }

        // Find with path compression
        int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);  // Path compression
            }
            return parent[u];
        }

        // Union by rank
        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            
            if (rootU != rootV) {
                // Union by rank
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    // Kruskal's Algorithm for Maximum Spanning Tree
    static int kruskalMaximumSpanningTree(int n, List<int[]> edges) {
        // Initialize Union-Find structure
        UnionFind uf = new UnionFind(n);

        // Sort the edges in descending order of weights
        edges.sort((a, b) -> Integer.compare(b[0], a[0]));  // Sort by weight in descending order

        int mstWeight = 0;
        int mstEdges = 0;

        // Process each edge
        for (int[] edge : edges) {
            int weight = edge[0];
            int u = edge[1];
            int v = edge[2];

            // If u and v are in different sets, include the edge in the MST
            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                mstWeight += weight;
                mstEdges++;
                // If we've added n-1 edges, the tree is complete
                if (mstEdges == n - 1) {
                    break;
                }
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) throws IOException {
        // Set up BufferedReader and BufferedWriter for efficient I/O
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(reader.readLine().trim());  // Number of test cases
        
        while (t-- > 0) {
            // Read N and M
            String[] nm = reader.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);  // number of nodes
            int m = Integer.parseInt(nm[1]);  // number of edges
            
            List<int[]> edges = new ArrayList<>();
            
            // Read all edges
            for (int i = 0; i < m; i++) {
                String[] edgeData = reader.readLine().split(" ");
                int u = Integer.parseInt(edgeData[0]) - 1;  // Convert to 0-indexed
                int v = Integer.parseInt(edgeData[1]) - 1;  // Convert to 0-indexed
                int w = Integer.parseInt(edgeData[2]);
                edges.add(new int[]{w, u, v});
            }
            
            // Find the weight of the Maximum Spanning Tree
            int result = kruskalMaximumSpanningTree(n, edges);
            writer.write(result + "\n");
        }

        // Flush and close the writer
        writer.flush();
        writer.close();
        reader.close();
    }
}
