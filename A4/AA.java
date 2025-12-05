import java.io.*;
import java.util.StringTokenizer;

public class AA {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer inp = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(inp.nextToken());  // number of nodes
        int m = Integer.parseInt(inp.nextToken());  // number of edges

        int[][] matrix = new int[n][n];

        // --- READ u ARRAY ---
        int[] u = new int[m];
        inp = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) u[i] = Integer.parseInt(inp.nextToken());

        // --- READ v ARRAY ---
        int[] v = new int[m];
        inp = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) v[i] = Integer.parseInt(inp.nextToken());

        // --- READ w ARRAY ---
        int[] w = new int[m];
        inp = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) w[i] = Integer.parseInt(inp.nextToken());

        // --- BUILD MATRIX ---
        for (int i = 0; i < m; i++) {
            addThat(matrix, u[i], v[i], w[i]);
        }

        // --- OUTPUT MATRIX ---
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(matrix[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
    }

    static void addThat(int[][] arr, int from, int to, int weight){
        arr[from-1][to-1] = weight;  // directed edge
    }
}
