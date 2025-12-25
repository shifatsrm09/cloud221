import java.io.*;
import java.util.*;

public class G {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        boolean[] present = new boolean[26];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            for (char c : words[i].toCharArray()) {
                present[c - 'a'] = true;
            }
        }

        // Graph and indegree
        List<Integer>[] graph = new ArrayList[26];
        for (int i = 0; i < 26; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[26];

        // Build graph from word order
        for (int i = 0; i < N - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            int len = Math.min(w1.length(), w2.length());
            boolean found = false;

            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {
                    graph[c1 - 'a'].add(c2 - 'a');
                    indegree[c2 - 'a']++;
                    found = true;
                    break;
                }
            }

            // Invalid case: prefix issue
            if (!found && w1.length() > w2.length()) {
                bw.write("-1");
                bw.flush();
                return;
            }
        }

        // Lexicographically smallest topological sort
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int totalChars = 0;

        for (int i = 0; i < 26; i++) {
            if (present[i]) {
                totalChars++;
                if (indegree[i] == 0) {
                    pq.offer(i);
                }
            }
        }

        StringBuilder order = new StringBuilder();

        while (!pq.isEmpty()) {
            int u = pq.poll();
            order.append((char) (u + 'a'));

            for (int v : graph[u]) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    pq.offer(v);
                }
            }
        }

        // Cycle detection
        if (order.length() != totalChars) {
            bw.write("-1");
        } else {
            bw.write(order.toString());
        }

        bw.flush();
    }
}
