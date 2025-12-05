import java.io.*;
import java.util.StringTokenizer;

public class BB {

    // Node class for adjacency list (linked-list style)
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

        // adjacency list array
        Node[] head = new Node[N + 1];

        // Read edges in u v w format
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // Insert edge u -> v with weight w
            head[u] = new Node(v, w, head[u]);
        }

        // Output adjacency list
        for (int i = 1; i <= N; i++) {
            bw.write(i + ":");

            Node cur = head[i];
            while (cur != null) {
                bw.write(" (" + cur.to + "," + cur.weight + ")");
                cur = cur.next;
            }

            bw.write("\n");
        }

        bw.flush();
    }
}
