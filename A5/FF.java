import java.io.*;
import java.util.*;

public class FF {

    static ArrayList<Integer>[] adj;
    static int[] state; // 0 = unvisited, 1 = visiting, 2 = done
    static boolean hasCycle = false;
    static int N, M;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // --- READ u ARRAY ---
        int[] u = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        // --- READ v ARRAY ---
        int[] v = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        // Build directed graph using u[i] → v[i]
        for (int i = 0; i < M; i++) {
            adj[u[i]].add(v[i]);
        }

        state = new int[N + 1];

        // run DFS from all unvisited nodes
        for (int i = 1; i <= N; i++) {
            if (state[i] == 0) {
                dfs(i);
                if (hasCycle) break;
            }
        }

        bw.write(hasCycle ? "YES\n" : "NO\n");
        bw.flush();
    }

    static void dfs(int u) {
        state[u] = 1; // visiting

        for (int v : adj[u]) {
            if (state[v] == 0) {
                dfs(v);
            } else if (state[v] == 1) {
                hasCycle = true; // found back-edge
                return;
            }
            if (hasCycle) return;
        }

        state[u] = 2; // done
    }
}
