import java.io.*;
import java.util.*;

public class F {
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        buildOrder(0, n - 1);
        bw.write(sb.toString().trim());
    }

    static void buildOrder(int l, int r) {
        if (l > r) return ;
        int mid = (l + r) / 2;
        sb.append(arr[mid]).append(" ");   // Insert this element first
        buildOrder(l, mid - 1);            // Left subarray
        buildOrder(mid + 1, r);            // Right subarray
    }
}
