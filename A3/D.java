import java.io.*;
import java.util.*;

public class D {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a11 = Long.parseLong(st.nextToken());
            long a12 = Long.parseLong(st.nextToken());
            long a21 = Long.parseLong(st.nextToken());
            long a22 = Long.parseLong(st.nextToken());
            long X = Long.parseLong(br.readLine());

            long[] A = {a11 % MOD, a12 % MOD, a21 % MOD, a22 % MOD};
            long[] res = matrixPower(A, X);

            sb.append(res[0]).append(" ").append(res[1]).append("\n");
            sb.append(res[2]).append(" ").append(res[3]).append("\n");
        }

        System.out.print(sb.toString());
    }

    static long[] matrixPower(long[] A, long exp) {
        long[] result = {1, 0, 0, 1};  // Identity matrix

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, A);
            }
            A = multiply(A, A);
            exp >>= 1;
        }
        return result;
    }

    static long[] multiply(long[] A, long[] B) {
        return new long[]{
            (A[0] * B[0] % MOD + A[1] * B[2] % MOD) % MOD,
            (A[0] * B[1] % MOD + A[1] * B[3] % MOD) % MOD,
            (A[2] * B[0] % MOD + A[3] * B[2] % MOD) % MOD,
            (A[2] * B[1] % MOD + A[3] * B[3] % MOD) % MOD
        };
    }
}
