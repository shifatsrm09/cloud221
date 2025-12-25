import java.io.*;
import java.util.*;

public class DC {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int farNode;
    static int maxDist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            String[] p = br.readLine().split(" ");
            int u = Integer.parseInt(p[0]);
            int v = Integer.parseInt(p[1]);
            adj[u].add(v);
            adj[v].add(u);
        }

        // STEP 1: DFS from 1 → find farthest node A
        visited = new boolean[N + 1];
        farNode = 1;
        maxDist = -1;
        dfs(1, 0);

        int A = farNode;

        // STEP 2: DFS from A → find farthest node B and diameter
        visited = new boolean[N + 1];
        maxDist = -1;
        dfs(A, 0);

        int B = farNode;
        int diameter = maxDist;

        // Output
        System.out.println(diameter);
        System.out.println(A + " " + B);
    }

    static void dfs(int u, int dist) {

        visited[u] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farNode = u;
        }

        for (int v : adj[u]) {
            if (!visited[v]) dfs(v, dist + 1);
        }
    }
}
