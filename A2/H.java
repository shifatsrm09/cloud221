import java.io.*;
import java.util.StringTokenizer;

public class H {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine().trim());  

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); 
            int x = Integer.parseInt(st.nextToken()); 
            
            int result = k + (k - 1) / (x - 1);
            
            bw.write(String.valueOf(result));
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
