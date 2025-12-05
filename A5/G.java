import java.io.*;
import java.util.*;

public class G {

    static int R, C;
    static char[][] grid;
    static boolean[][] visited;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) grid[i] = br.readLine().toCharArray();

        int answer = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && grid[i][j] != '#') {
                    // BFS this component
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }

        bw.write(answer + "\n");
        bw.flush();
    }

    static int bfs(int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        visited[sr][sc] = true;

        int diamonds = 0;
        if (grid[sr][sc] == 'D') diamonds++;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (visited[nr][nc]) continue;
                if (grid[nr][nc] == '#') continue;

                visited[nr][nc] = true;
                if (grid[nr][nc] == 'D') diamonds++;
                q.add(new int[]{nr, nc});
            }
        }

        return diamonds;
    }
}
