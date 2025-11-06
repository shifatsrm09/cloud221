import java.io.*;
import java.util.*;

public class B {
    static class Fenwick {
        int[] tree;
        int size;
        Fenwick(int n) {
            size = n;
            tree = new int[n + 2];
        }
        void update(int idx, int delta) {
            while (idx <= size) {
                tree[idx] += delta;
                idx += idx & -idx;
            }
        }
        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        // Collect all relevant values (arr[i] and arr[i]^2)
        long[] vals = new long[2 * N];
        int idx = 0;
        for (int x : arr) {
            vals[idx++] = x;
            vals[idx++] = 1L * x * x;
        }

        // Sort and remove duplicates
        Arrays.sort(vals);
        int unique = 0;
        for (int i = 0; i < vals.length; i++) {
            if (i == 0 || vals[i] != vals[i - 1]) vals[unique++] = vals[i];
        }

        // Map value → compressed index
        Fenwick bit = new Fenwick(unique + 2);
        long count = 0;

        for (int j = 0; j < N; j++) {
            long sq = 1L * arr[j] * arr[j];
            int compSq = Arrays.binarySearch(vals, 0, unique, sq) + 1;
            int total = bit.query(unique);
            int lessOrEqual = bit.query(compSq);
            count += total - lessOrEqual;

            if (arr[j] > 0) {
                int compVal = Arrays.binarySearch(vals, 0, unique, arr[j]) + 1;
                bit.update(compVal, 1);
            }
        }

        System.out.println(count);
    }
}
