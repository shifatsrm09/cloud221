import java.io.*;
import java.util.*;

public class ET{

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());   // number of test cases

        while (T-- > 0) {

            // Read N, M, S, Q
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            // Build graph
            List<Integer>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            // Read edges
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            // Read source nodes
            st = new StringTokenizer(br.readLine());
            int[] sources = new int[S];
            for (int i = 0; i < S; i++) {
                sources[i] = Integer.parseInt(st.nextToken());
            }

            // Read destination nodes
            st = new StringTokenizer(br.readLine());
            int[] destinations = new int[Q];
            for (int i = 0; i < Q; i++) {
                destinations[i] = Integer.parseInt(st.nextToken());
            }

            // Multi-source BFS
            int[] dist = new int[N + 1];
            Arrays.fill(dist, -1);

            ArrayDeque<Integer> queue = new ArrayDeque<>();

            for (int src : sources) {
                if (dist[src] == -1) {
                    dist[src] = 0;
                    queue.add(src);
                }
            }

            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : graph[u]) {
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        queue.add(v);
                    }
                }
            }

            // Output answers for this test case
            StringBuilder sb = new StringBuilder();
            for (int d : destinations) {
                sb.append(dist[d]).append(" ");
            }

            System.out.println(sb.toString().trim());
        }
    }
}
