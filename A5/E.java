import java.io.*;
import java.util.*;

public class E {

    static ArrayList<Integer>[] adj;
    static int[] subtree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        subtree = new int[N + 1];
        visited = new boolean[N + 1];

        dfs(R);

        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            int x = Integer.parseInt(br.readLine());
            bw.write(subtree[x] + "\n");
        }

        bw.flush();
    }

    static void dfs(int u) {
        visited[u] = true;
        subtree[u] = 1;

        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs(v);
                subtree[u] += subtree[v];
            }
        }
    }
}
