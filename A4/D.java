import java.io.*;
import java.util.StringTokenizer;

public class D {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] u = new int[M];
        int[] v = new int[M];

        // Read u1..uM
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        // Read v1..vM
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        // Degree array (1-based indexing)
        int[] deg = new int[N + 1];

        // Compute degrees (handle self-loops carefully)
        for (int i = 0; i < M; i++) {
            int a = u[i];
            int b = v[i];

            if (a == b) {
                // self loop contributes 2 to the degree of node a
                deg[a] += 2;
            } else {
                deg[a]++;
                deg[b]++;
            }
        }

        int oddCount = 0;
        for (int i = 1; i <= N; i++) {
            if (deg[i] % 2 != 0) {
                oddCount++;
            }
        }

        // Eulerian path exists if number of odd degree vertices is 0 or 2
        if (oddCount == 0 || oddCount == 2) {
            bw.write("YES\n");
        } else {
            bw.write("NO\n");
        }

        bw.flush();
    }
}
