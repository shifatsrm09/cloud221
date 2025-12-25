import java.io.*;
import java.util.*;

public class AT {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //  Build adjacency list
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        //  Build indegree array
        int[] indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
              st = new StringTokenizer(br.readLine());
              int A = Integer.parseInt(st.nextToken());
              int B = Integer.parseInt(st.nextToken());

            adj[A].add(B);
            indegree[B]++;
        }

        //Put all indegree-0 nodes in queue
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        //  Process queue (Kahn’s Algorithm)
        int count = 0;
        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            int u = queue.poll();    // take a node with indegree 0
            result.append(u).append(" ");
            count++;

            // reduce indegree of neighbors
            for (int v : adj[u]) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        //  Check if we processed all courses
        if (count == N) {
            bw.write(result.toString().trim());
            bw.newLine();
        } else {
           bw.write(String.valueOf("-1"));
           bw.newLine();
        }

        }

        bw.flush();
    }
}
