import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class H{

    // Fast scanner (no StringTokenizer needed)
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return -1;
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = fs.nextInt();
        int Q = fs.nextInt();

        int[] deg = new int[N + 1];

        // First pass: count degrees
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (gcd(i, j) == 1) {
                    deg[i]++;
                    deg[j]++;
                }
            }
        }

        // Allocate adjacency arrays
        int[][] adj = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            adj[i] = new int[deg[i]];
        }

        int[] ptr = new int[N + 1];

        // Second pass: fill adjacency lists (automatically sorted)
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (gcd(i, j) == 1) {
                    adj[i][ptr[i]++] = j;
                    adj[j][ptr[j]++] = i;
                }
            }
        }

        // Answer queries
        while (Q-- > 0) {
            int X = fs.nextInt();
            int K = fs.nextInt();

            if (K <= 0 || K > deg[X]) {
                bw.write("-1\n");
            } else {
                bw.write(adj[X][K - 1] + "\n");
            }
        }

        bw.flush();
    }
}
