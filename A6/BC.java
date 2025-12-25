import java.io.*;
import java.util.*;

public class BC {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // adjacency list for undirected graph
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // read edges (tackles)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] color = new int[N + 1];
        Arrays.fill(color, -1);   // -1 = uncolored

        int answer = 0;

        // run BFS on each unvisited component
        for (int i = 1; i <= N; i++) {

            if (color[i] == -1) {
                answer += bfsColorAndMax(i, adj, color);
            }
        }

        System.out.println(answer);
    }

    // BFS: color component in 2 colors and return max color count
    static int bfsColorAndMax(int start, ArrayList<Integer>[] adj, int[] color) {

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        color[start] = 0;

        int count0 = 1;   // number of nodes colored 0
        int count1 = 0;   // number of nodes colored 1

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : adj[u]) {

                if (color[v] == -1) {  
                    color[v] = 1 - color[u];

                    if (color[v] == 0) count0++;
                    else count1++;

                    q.add(v);
                }
            }
        }

        return Math.max(count0, count1);
    }
}
