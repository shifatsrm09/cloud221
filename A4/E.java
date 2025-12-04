import java.io.*;
import java.util.StringTokenizer;

public class E {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] u = new int[M];
        int[] v = new int[M];

        // read u1..uM
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        // read v1..vM
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        int[] indeg = new int[N + 1];
        int[] outdeg = new int[N + 1];

        // calculate indegree and outdegree
        for (int i = 0; i < M; i++) {
            int from = u[i];
            int to   = v[i];

            outdeg[from]++;
            indeg[to]++;
        }

        // output indegree - outdegree
        for (int i = 1; i <= N; i++) {
            bw.write((indeg[i] - outdeg[i]) + " ");
        }
        bw.newLine();
        bw.flush();
    }
}
