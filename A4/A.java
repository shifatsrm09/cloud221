

import java.io.*;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer inp = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(inp.nextToken());
        int t = Integer.parseInt(inp.nextToken());
        int[][] matrix = new int[n][n];
        
        while (t-->0) {
            inp = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(inp.nextToken()), to = Integer.parseInt(inp.nextToken()), weight = Integer.parseInt(inp.nextToken());
            addThat(matrix,from,to,weight);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                bw.write(matrix[i][j]+ " ");
            }
            bw.newLine();
        }
        bw.flush();
        
    }

    static void addThat(int[][] arr , int from , int to, int weight){
        arr[from-1][to-1] = weight;
    }
}
