import java.io.*;
import java.util.*;

public class Solution {
    static class Pair {
        int value, index;
        public Pair(String v, int i) {
            this.value = Integer.parseInt(v);
            this.index = i;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int s = Integer.parseInt(input[1]);

            Pair[] pairs = new Pair[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                pairs[j] = new Pair(st.nextToken(), j + 1);
            }

            Arrays.sort(pairs, Comparator.comparingInt(a -> a.value));

            boolean found = false;

            for (int j = 0; j < n; j++) {
                int start = 0, end = n - 1;

                while (start < end) {
                    if (start == j) { start++; continue; }
                    if (end == j) { end--; continue; }

                    int sum = pairs[j].value + pairs[start].value + pairs[end].value;

                    if (sum == s) {
                        bw.write(pairs[j].index + " " + pairs[start].index + " " + pairs[end].index);
                        bw.newLine();
                        found = true;
                        break;
                    } else if (sum < s) {
                        start++;
                    } else {
                        end--;
                    }
                }

                if (found) break;
            }

            if (!found) {
                bw.write("-1");
                bw.newLine();
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
