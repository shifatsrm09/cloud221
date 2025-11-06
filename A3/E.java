import java.io.*;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] s = br.readLine().split(" ");
            long a = Long.parseLong(s[0]);
            long n = Long.parseLong(s[1]);
            long m = Long.parseLong(s[2]);
            bw.write(sumPowers(a, n, m) + "\n");
        }
        bw.flush();
    }

    static long sumPowers(long a, long n, long m) {
        if (n == 0) return 0;
        if (a % m == 0) return 0;
        if (a % m == 1) return n % m;

        if (n == 1) return a % m;

        long half = n / 2;
        long s = sumPowers(a, half, m);
        long pow = modPow(a, half, m);

        if (n % 2 == 0) { // even
            return (s * (1 + pow)) % m;
        } else { // odd
            long extra = modPow(a, n, m);
            return (s * (1 + pow) % m + extra) % m;
        }
    }

    static long modPow(long a, long n, long m) {
        long res = 1;
        a %= m;
        while (n > 0) {
            if ((n & 1) == 1) res = res * a % m;
            a = a * a % m;
            n >>= 1;
        }
        return res;
    }
}
