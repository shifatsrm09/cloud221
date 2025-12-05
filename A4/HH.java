import java.io.*;
import java.util.*;

public class HH {

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        // Build graph using LCM condition
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (lcm(i, j) <= N) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }

        // Sort adjacency lists
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }

        // Answer queries
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if (K > adj[X].size()) {
                bw.write("-1\n");
            } else {
                bw.write(adj[X].get(K - 1) + "\n");
            }
        }

        bw.flush();
    }
}
