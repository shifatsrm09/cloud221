import java.io.*;
import java.util.*;

public class DD {

    static int N, M, S, Dst, K;
    static int[] head, to, next;
    static int edgeIdx;

    static int[] dist1, dist2, par1, par2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        Dst = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        head = new int[N + 1];
        Arrays.fill(head, -1);

        // Need M edges → directed graph
        to = new int[M];
        next = new int[M];
        edgeIdx = 0;

        // Read array u[1..M]
        int[] u = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        // Read array v[1..M]
        int[] v = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        // Build directed graph using u[i] → v[i]
        for (int i = 0; i < M; i++) {
            addEdge(u[i], v[i]);
        }

        dist1 = new int[N + 1];
        dist2 = new int[N + 1];
        par1 = new int[N + 1];
        par2 = new int[N + 1];

        bfs(S, dist1, par1);

        // S -> K must exist
        if (dist1[K] == -1) {
            bw.write("-1\n");
            bw.flush();
            return;
        }

        bfs(K, dist2, par2);

        // K -> D must exist
        if (dist2[Dst] == -1) {
            bw.write("-1\n");
            bw.flush();
            return;
        }

        // Reconstruct S -> K
        int[] path1 = new int[N + 1];
        int len1 = 0;
        int cur = K;
        while (cur != -1) {
            path1[len1++] = cur;
            cur = par1[cur];
        }
        reverse(path1, len1);

        // Reconstruct K -> D
        int[] path2 = new int[N + 1];
        int len2 = 0;
        cur = Dst;
        while (cur != -1) {
            path2[len2++] = cur;
            cur = par2[cur];
        }
        reverse(path2, len2);

        int totalEdges = (len1 - 1) + (len2 - 1);
        bw.write(totalEdges + "\n");

        // print S...K
        for (int i = 0; i < len1; i++) {
            bw.write(path1[i] + " ");
        }

        // print K...D but skip duplicate K
        for (int i = 1; i < len2; i++) {
            bw.write(path2[i] + " ");
        }

        bw.write("\n");
        bw.flush();
    }

    static void addEdge(int u, int v) {
        to[edgeIdx] = v;
        next[edgeIdx] = head[u];
        head[u] = edgeIdx++;
    }

    static void bfs(int start, int[] dist, int[] parent) {
        Arrays.fill(dist, -1);
        int[] q = new int[N + 5];
        int front = 0, back = 0;

        q[back++] = start;
        dist[start] = 0;
        parent[start] = -1;

        while (front < back) {
            int u = q[front++];

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    parent[v] = u;
                    q[back++] = v;
                }
            }
        }
    }

    static void reverse(int[] arr, int len) {
        int i = 0, j = len - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }
}
