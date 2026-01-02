import java.io.*;
import java.util.*;

public class A {

    static int[] parent;
    static int[] size;

    // Find with path compression
    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // Union by size
    static int union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) {
            return size[ra];
        }

        // attach smaller to larger
        if (size[ra] < size[rb]) {
            parent[ra] = rb;
            size[rb] += size[ra];
            return size[rb];
        } else {
            parent[rb] = ra;
            size[ra] += size[rb];
            return size[ra];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int result = union(a, b);
            out.append(result).append('\n');
        }

        System.out.print(out.toString());
    }
}
