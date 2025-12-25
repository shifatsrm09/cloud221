import java.io.*;
import java.util.*;

public class A {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());


        // adjacency list
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] indegree = new int[N + 1];

        // read edges A -> B
        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adj[A].add(B);
            indegree[B]++;
        }

        // queue for nodes with indegree 0
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++)
            if (indegree[i] == 0) q.add(i);

        int count = 0;
        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            int u = q.poll();
            sb.append(u).append(" ");
            count++;

            for (int v : adj[u]) {
                indegree[v]--;
                if (indegree[v] == 0) q.add(v);
            }
        }

        // if not all nodes processed → cycle exists
        if (count != N) {
            bw.write("-1\n");
        } else {
            bw.write(sb.toString());
        }

        bw.flush();
    }
}
