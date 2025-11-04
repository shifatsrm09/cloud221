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
        int queryRange(int l, int r) {
            return query(r) - query(l - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            if (arr[i] > 0) set.add((long) arr[i]);
            set.add((long) arr[i] * arr[i]);
        }

        Map<Long, Integer> compress = new HashMap<>();
        int idx = 1;
        for (long val : set) compress.put(val, idx++);

        Fenwick bit = new Fenwick(set.size());
        long count = 0;

        for (int j = 0; j < N; j++) {
            long square = (long) arr[j] * arr[j];
            int compSquare = compress.get(square);

            int total = bit.query(set.size());
            int lessOrEqual = bit.query(compSquare);
            count += (total - lessOrEqual); // numbers > arr[j]^2

            if (arr[j] > 0) {
                int compVal = compress.get((long) arr[j]);
                bit.update(compVal, 1);
            }
        }

        System.out.println(count);
    }
}
