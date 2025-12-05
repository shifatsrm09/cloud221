import java.io.*;
import java.util.StringTokenizer;

public class CC {

    static class Node {
        int to;
        Node next;
        Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // adjacency list: head[i] = linked list of neighbors
        Node[] head = new Node[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken()); // number of neighbors of node i

            for (int j = 0; j < k; j++) {
                int to = Integer.parseInt(st.nextToken());

                // Add node i -> to in adjacency list
                head[i] = new Node(to, head[i]);
            }
        }

        // PRINT adjacency list
        for (int i = 0; i < N; i++) {
            bw.write(i + ":");

            Node cur = head[i];
            while (cur != null) {
                bw.write(" " + cur.to);
                cur = cur.next;
            }

            bw.newLine();
        }

        bw.flush();
    }
}
