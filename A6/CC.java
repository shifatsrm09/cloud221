import java.io.*;
import java.util.*;

public class CC {

    static int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
    static int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

    public static void main (String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;

        // Same position
        if (x1 == x2 && y1 == y2) {
            bw.write("0");
            bw.flush();
            return;
        }

        // Parity check
        if ((x1 + y1) % 2 != (x2 + y2) % 2) {
            bw.write("-1");
            bw.flush();
            return;
        }

        int[][] dist1 = new int[N][N];
        int[][] dist2 = new int[N][N];
        for (int[] row : dist1) Arrays.fill(row, -1);
        for (int[] row : dist2) Arrays.fill(row, -1);

        Queue<int[]> q1 = new ArrayDeque<>();
        Queue<int[]> q2 = new ArrayDeque<>();

        q1.offer(new int[]{x1, y1});
        q2.offer(new int[]{x2, y2});
        dist1[x1][y1] = 0;
        dist2[x2][y2] = 0;

        while (!q1.isEmpty() && !q2.isEmpty()) {

            // Expand from start side
            int res = expand(q1, dist1, dist2, N);
            if (res != -1) {
                bw.write(String.valueOf(res));
                bw.flush();
                return;
            }

            // Expand from target side
            res = expand(q2, dist2, dist1, N);
            if (res != -1) {
                bw.write(String.valueOf(res));
                bw.flush();
                return;
            }
        }

        bw.write("-1");
        bw.flush();
    }

    static int expand(Queue<int[]> q, int[][] distA, int[][] distB, int N) {
        int size = q.size();

        while (size-- > 0) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (distA[nx][ny] != -1) continue;

                distA[nx][ny] = distA[x][y] + 1;

                if (distB[nx][ny] != -1) {
                    return distA[nx][ny] + distB[nx][ny];
                }

                q.offer(new int[]{nx, ny});
            }
        }
        return -1;
    }
}
