import java.io.*;

public class T1{
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

       

        for (int i = 0; i < T; i++) {
             int n = Integer.parseInt(br.readLine());
              if (n%2 == 0) bw.write(n  + " is an Even number.\n");
              else bw.write(n + " is an Odd number.\n");
        }

        bw.flush();
        br.close();
        bw.close();


    }
}