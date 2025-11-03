import java.io.*;
import java.util.*;

public class G{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int T_pair = Integer.parseInt(parts[1]);

        int[] arr = arrMkr(br.readLine(), n);
        Arrays.sort(arr); // ensure sorted

        int[][] pair = new int[T_pair][2];
        for (int i = 0; i < T_pair; i++) {
            String[] pairXY = br.readLine().split(" ");
            pair[i][0] = Integer.parseInt(pairXY[0]);
            pair[i][1] = Integer.parseInt(pairXY[1]);
        }

        for (int i = 0; i < T_pair; i++) {
            int L = pair[i][0], R = pair[i][1];

            // find first index >= L
            int x = lowerBound(arr, L);
            // find last index <= R
            int y = upperBound(arr, R);

       
            int count = y - x + 1;
          

            bw.write(count + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int[] arrMkr(String s, int n) {
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(s);
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    // binary search for first element >= target
    static int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    // binary search for first element > target
    static int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l-1;
    }
}