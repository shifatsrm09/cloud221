import java.io.*;
import java.util.*;

public class CC {

    static int N, M, S, D;
    static ArrayList<Integer>[] adj;
    static int[] distS, distD;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // READ M LINES OF EDGES IN U V FORMAT
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        // Sort adjacency lists to ensure lexicographically smallest path
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }

        distS = new int[N + 1];
        distD = new int[N + 1];

        bfs(S, distS);
        if (distS[D] == -1) {
            bw.write("-1\n");
            bw.flush();
            return;
        }

        bfs(D, distD); // BFS from destination

        int totalDist = distS[D];

        // Build lexicographically smallest shortest path
        ArrayList<Integer> path = new ArrayList<>();
        int cur = S;
        path.add(cur);

        while (cur != D) {
            int next = -1;
            for (int nb : adj[cur]) {
                if (distS[nb] == distS[cur] + 1 &&
                    distS[nb] + distD[nb] == totalDist) {
                    next = nb;
                    break; // adjacency list is sorted → first valid is lexicographically smallest
                }
            }
            if (next == -1) break; // safety fallback
            path.add(next);
            cur = next;
        }

        sb.append(path.size() - 1).append('\n');
        for (int node : path) sb.append(node).append(' ');
        sb.append('\n');

        bw.write(sb.toString());
        bw.flush();
    }

    static void bfs(int start, int[] dist) {
        Arrays.fill(dist, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int nb : adj[u]) {
                if (dist[nb] == -1) {
                    dist[nb] = dist[u] + 1;
                    q.add(nb);
                }
            }
        }
    }
}
