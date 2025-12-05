import java.io.*;
import java.util.StringTokenizer;

public class EE {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indeg = new int[N + 1];
        int[] outdeg = new int[N + 1];

        // Read M lines of "u v"
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            outdeg[u]++;
            indeg[v]++;
        }

        // Output indegree - outdegree
        for (int i = 1; i <= N; i++) {
            bw.write((indeg[i] - outdeg[i]) + " ");
        }
        bw.newLine();
        bw.flush();
    }
}
