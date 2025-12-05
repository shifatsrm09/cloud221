import java.io.*;
import java.util.StringTokenizer;
public class C {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer inp = new StringTokenizer(br.readLine());

        long base = Long.parseLong(inp.nextToken());
        long exponent = Long.parseLong(inp.nextToken());

        int mod = 107;

        long result = calculatePower(base, exponent, mod);

        bw.write(String.valueOf( result % mod ));
        bw.newLine();
        
        bw.flush();
        br.close();
        bw.close();
   
    }

    static long calculatePower(long x, long n, int mod) {
        long result = 1;
        long base = x % mod;

        while (n > 0) {
            if (n % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            n = n / 2;
        }

        return result;
    }
}