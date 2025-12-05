import java.io.*;
import java.util.*;

public class EE {

    static ArrayList<Integer>[] adj;
    static int[] subtree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // Initialize adjacency lists
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // --- READ u ARRAY ---
        int[] u = new int[N - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        // --- READ v ARRAY ---
        int[] v = new int[N - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        // Build undirected tree edges
        for (int i = 0; i < N - 1; i++) {
            adj[u[i]].add(v[i]);
            adj[v[i]].add(u[i]);
        }

        subtree = new int[N + 1];
        visited = new boolean[N + 1];

        // Compute subtree sizes from root R
        dfs(R);

        // Process queries
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            int x = Integer.parseInt(br.readLine());
            bw.write(subtree[x] + "\n");
        }

        bw.flush();
    }

    static void dfs(int u) {
        visited[u] = true;
        subtree[u] = 1;  // count itself

        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs(v);
                subtree[u] += subtree[v];
            }
        }
    }
}
