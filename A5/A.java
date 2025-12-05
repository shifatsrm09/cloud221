import java.io.*;
import java.util.*;

public class A {
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

        // input edges
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        // BFS
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        StringBuilder output = new StringBuilder();

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            output.append(u).append(" ");

            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }

        System.out.println(output.toString());
    }
}
