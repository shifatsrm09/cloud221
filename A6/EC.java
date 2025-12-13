import java.io.*;
import java.util.*;

public class EC {
    
    public static void main(String[] args) throws IOException {
        // BufferedReader and StringTokenizer for fast input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // Read N, M, S, Q
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // Graph adjacency list
        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // Read edges and build the graph
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // Read the source nodes
        st = new StringTokenizer(br.readLine());
        int[] sources = new int[S];
        for (int i = 0; i < S; i++) {
            sources[i] = Integer.parseInt(st.nextToken());
        }

        // Read the destination nodes
        st = new StringTokenizer(br.readLine());
        int[] destinations = new int[Q];
        for (int i = 0; i < Q; i++) {
            destinations[i] = Integer.parseInt(st.nextToken());
        }

        // BFS to find shortest paths from all source nodes
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();

        // Initialize the queue with all source nodes and set their distance to 0
        for (int source : sources) {
            if (dist[source] == -1) { // If the source has not been visited yet
                dist[source] = 0;
                queue.offer(source);
            }
        }

        // Perform BFS
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currentDist = dist[node];

            for (int neighbor : graph[node]) {
                if (dist[neighbor] == -1) { // If not visited
                    dist[neighbor] = currentDist + 1;
                    queue.offer(neighbor);
                }
            }
        }

        // Output answers for each destination
        StringBuilder sb = new StringBuilder();
        for (int dest : destinations) {
            sb.append(dist[dest]).append(" ");
        }

        // Print the result
        System.out.println(sb.toString().trim());
    }
}
