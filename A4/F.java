import java.io.*;
import java.util.StringTokenizer;

public class F {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        // 8 possible king moves
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

        // Max 8 moves → store them in small arrays
        int[][] moves = new int[8][2];
        int count = 0;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                moves[count][0] = nx;
                moves[count][1] = ny;
                count++;
            }
        }

        // Sort moves: first by row, then column
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (moves[j][0] < moves[i][0] ||
                   (moves[j][0] == moves[i][0] && moves[j][1] < moves[i][1])) {

                    int tx = moves[i][0]; 
                    int ty = moves[i][1];
                    moves[i][0] = moves[j][0];
                    moves[i][1] = moves[j][1];
                    moves[j][0] = tx;
                    moves[j][1] = ty;
                }
            }
        }

        // Output
        bw.write(count + "\n");
        for (int i = 0; i < count; i++) {
            bw.write(moves[i][0] + " " + moves[i][1] + "\n");
        }

        bw.flush();
    }
}
