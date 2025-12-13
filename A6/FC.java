import java.io.*;
import java.util.*;

public class FC {

    static List<Integer>[] primeFactors = new List[5001];

    static void precomputePrimeFactors() {
        for (int i = 1; i <= 5000; i++) {
            primeFactors[i] = new ArrayList<>();
        }

        for (int i = 2; i <= 5000; i++) {
            if (primeFactors[i].isEmpty()) { // i is prime
                for (int j = i; j <= 5000; j += i) {
                    primeFactors[j].add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        precomputePrimeFactors();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (s == t) {
                bw.write("0\n");
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[5001];
            int[] dist = new int[5001];
            Arrays.fill(dist, -1);

            queue.offer(s);
            visited[s] = true;
            dist[s] = 0;

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for (int factor : primeFactors[curr]) {
                    if (factor == curr) continue; // ✅ FIX

                    int next = curr + factor;

                    if (next <= 5000 && !visited[next]) {
                        visited[next] = true;
                        dist[next] = dist[curr] + 1;
                        queue.offer(next);
                    }
                }
            }

            bw.write(dist[t] + "\n");
        }

        bw.flush();
    }
}
