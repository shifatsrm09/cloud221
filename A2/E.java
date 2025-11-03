import java.io.*;
import java.util.StringTokenizer;

public class E {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int k = Integer.parseInt(parts[1]);
        int[] arr = arrMkr(br.readLine(), n);

        int maxLength = 0;
        int sum = 0;
        int start = 0;

        for (int end = 0; end < n; end++) {
            sum += arr[end];

            // shrink window from the left if sum > k
            while (sum > k && start <= end) {
                sum -= arr[start];
                start++;
            }

            // update maxLength
            maxLength = Math.max(maxLength, end - start + 1);
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
