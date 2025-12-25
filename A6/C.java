    import java.io.*;
    import java.util.*;
     
    public class C {
     
        static final int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
        static final int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};
     
        public static void main(String[] args) throws Exception {
     
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
     
            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], -1);
            }
     
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{x1, y1});
            dist[x1][y1] = 0;
     
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
     
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
     
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (dist[nx][ny] != -1) continue;
     
                    dist[nx][ny] = dist[x][y] + 1;
     
                    if (nx == x2 && ny == y2) {
                        bw.write(String.valueOf(dist[nx][ny]));
                        bw.flush();
                        return;
                    }
     
                    q.offer(new int[]{nx, ny});
                }
            }
     
            bw.write("-1");
            bw.flush();
        }
    }