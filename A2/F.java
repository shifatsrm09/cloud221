import java.io.*;
import java.util.*;

public class F {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = arrMkr(br.readLine(), n);
        int[] count = new int[n + 1]; 
        int distinct = 0;
        int left = 0, maxLength = 0;

        for (int right = 0; right < n; right++) {
            if (count[arr[right]] == 0) {
                distinct++;
            }
            count[arr[right]]++;

            // Shrink window if distinct elements exceed k
            while (distinct > k) {
                count[arr[left]]--;
                if (count[arr[left]] == 0) {
                    distinct--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        bw.write(maxLength + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    static int[] arrMkr(String s, int n) {
        StringTokenizer st = new StringTokenizer(s);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }
}
