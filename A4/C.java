import java.io.*;
import java.util.StringTokenizer;

public class C {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken()); // number of adjacent nodes

            for (int j = 0; j < k; j++) {
                int to = Integer.parseInt(st.nextToken());
                matrix[i][to] = 1;
            }
        }

        // print matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(matrix[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
    }
}
