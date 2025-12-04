import java.io.*;
import java.util.StringTokenizer;

public class G {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] board = new boolean[N + 1][M + 1];
        int[] x = new int[K];
        int[] y = new int[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            board[x[i]][y[i]] = true;
        }

        int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

        for (int i = 0; i < K; i++) {
            int cx = x[i], cy = y[i];

            for (int t = 0; t < 8; t++) {
                int nx = cx + dx[t];
                int ny = cy + dy[t];

                if (nx >= 1 && nx <= N && ny >= 1 && ny <= M) {
                    if (board[nx][ny]) {
                        bw.write("YES\n");
                        bw.flush();
                        return;
                    }
                }
            }
        }

        bw.write("NO\n");
        bw.flush();
    }
}
