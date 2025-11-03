import java.io.*;
import java.util.StringTokenizer;

public class D_better {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int alice_length = Integer.parseInt(br.readLine());
        int[] alice_arr = arrMkr(br.readLine(), alice_length);
        int bob_length = Integer.parseInt(br.readLine());
        int[] bob_arr = arrMkr(br.readLine(), bob_length);

        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();

        // merge and output directly
        while (i < alice_length && j < bob_length) {
            if (alice_arr[i] <= bob_arr[j]) sb.append(alice_arr[i++]).append(' ');
            else sb.append(bob_arr[j++]).append(' ');
        }
        while (i < alice_length) sb.append(alice_arr[i++]).append(' ');
        while (j < bob_length) sb.append(bob_arr[j++]).append(' ');

        bw.write(sb.toString());
        bw.flush();
    }

    static int[] arrMkr(String s, int n) {
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(s);
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        return arr;
    }
}
