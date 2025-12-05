import java.io.*;
import java.util.*;

public class AA {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // adjacency list
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // read u array
        int[] u = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        // read v array
        int[] v = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        // build graph
        for (int i = 0; i < M; i++) {
            graph[u[i]].add(v[i]);
            graph[v[i]].add(u[i]);
        }

        // BFS from node 1
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        StringBuilder output = new StringBuilder();

        visited[1] = true;
        queue.add(1);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            output.append(curr).append(" ");

            for (int nei : graph[curr]) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    queue.add(nei);
                }
            }
        }

        System.out.println(output.toString());
    }
}
