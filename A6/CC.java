import java.io.*;
import java.util.*;

public class CC {

    static int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
    static int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        System.out.println(bfsKnight(N, x1, y1, x2, y2));
    }

    static int bfsKnight(int N, int x1, int y1, int x2, int y2) {
        boolean[][] visited = new boolean[N + 1][N + 1];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.add(new int[]{x1, y1, 0});
        visited[x1][y1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], d = cur[2];

            if (x == x2 && y == y2) return d;

            for (int k = 0; k < 8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, d + 1});
                    }
                }
            }
        }

        return -1;
    }
}
