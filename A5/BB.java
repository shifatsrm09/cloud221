import java.io.*;
import java.util.*;

public class BB {

    static int[] head, to, next;
    static boolean[] visited;
    static int edgeIndex = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // adjacency list arrays
        head = new int[N + 1];
        Arrays.fill(head, -1);

        // undirected graph → 2*M edges
        to = new int[2 * M];
        next = new int[2 * M];

        // Read edges in normal u v line format
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            addEdge(u, v);  // u -> v
            addEdge(v, u);  // v -> u
        }

        visited = new boolean[N + 1];

        dfs(1);

        bw.write(sb.toString());
        bw.flush();
    }

    static void addEdge(int u, int v) {
        to[edgeIndex] = v;
        next[edgeIndex] = head[u];
        head[u] = edgeIndex;
        edgeIndex++;
    }

    static void dfs(int node) {
        visited[node] = true;
        sb.append(node).append(' ');

        for (int i = head[node]; i != -1; i = next[i]) {
            int nxt = to[i];
            if (!visited[nxt]) {
                dfs(nxt);
            }
        }
    }
}
