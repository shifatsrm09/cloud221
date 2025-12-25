import java.io.*;
import java.util.*;

public class DT {

    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int farNode;
    static int maxDist;

   public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // number of test cases

        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine());

            adj = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++)
                adj[i] = new ArrayList<>();

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                adj[v].add(u);
            }

            // STEP 1: DFS from node 1
            visited = new boolean[N + 1];
            farNode = 1;
            maxDist = -1;
            dfs(1, 0);

            int A = farNode;

            // STEP 2: DFS from A
            visited = new boolean[N + 1];
            maxDist = -1;
            dfs(A, 0);

            int B = farNode;
            int diameter = maxDist;

            // Output for this test case
            System.out.println(diameter);
            System.out.println(A + " " + B);
        }
    }

    static void dfs(int u, int dist) {

        visited[u] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farNode = u;
        }

        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs(v, dist + 1);
            }
        }
    }
}
