import java.io.*;
import java.io.IOException;
import java.util.StringTokenizer;

public class B {

    // Node class for adjacency list
    static class Node {
        int to;
        int weight;
        Node next;
        Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] first = br.readLine().trim().split(" ");
        int N = Integer.parseInt(first[0]);
        int M = Integer.parseInt(first[1]);

        int[] u = new int[M];
        int[] v = new int[M];
        int[] w = new int[M];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
           for (int i = 0; i < M; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

          st = new StringTokenizer(br.readLine());
           for (int i = 0; i < M; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }


        Node[] head = new Node[N + 1];

        // Build adjacency list (insert at head — O(1))
        for (int i = 0; i < M; i++) {
            int from = u[i];
            head[from] = new Node(v[i], w[i], head[from]);
        }

        // Output
        for (int i = 1; i <= N; i++) {
            bw.write(i + ":");

            Node cur = head[i];

            while (cur != null) {
      

                // Format: (to,weight)
                bw.write(" (" + cur.to + "," + cur.weight + ")");
                cur = cur.next;
            }

            bw.write("\n");
        }

        bw.flush();
    }
}
