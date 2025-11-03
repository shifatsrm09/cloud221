    import java.io.*;
    import java.util.StringTokenizer;
     
    public class B {
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
     
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nSize = Integer.parseInt(st.nextToken());  
            int mSize = Integer.parseInt(st.nextToken()); 
            int target = Integer.parseInt(st.nextToken()); 
     
            int[] N = arrMkr(br.readLine(), nSize);
            int[] M = arrMkr(br.readLine(), mSize);
     
            int i = 0;          // pointer for N (smallest value)
            int j = mSize - 1;  // pointer for M (largest value)
            int bestDiff = Math.abs( (N[i]+M[j] ) - target);
            int bestI = 0, bestJ = 0;
     
            while (i < nSize && j >= 0) {
                int sum = N[i] + M[j];
                int diff = Math.abs(sum - target);
     
     
                if (diff < bestDiff) {
                    bestDiff = diff;
                    bestI = i;
                    bestJ = j;
                }
     
              
                if (sum < target) {
                    i++;  
                } else {
                    j--;  
                }
            }
     
            bw.write((bestI + 1) + " " + (bestJ + 1));
            bw.flush();
            br.close();
            bw.close();
        }
     
        static int[] arrMkr(String s, int n) {
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(s);
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(st.nextToken());
            return arr;
        }
    }