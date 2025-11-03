    import java.io.*;
    import java.util.StringTokenizer;
     
    public class A {
        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer input_alpha = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(input_alpha.nextToken()) , target = Integer.parseInt(input_alpha.nextToken());
            int[] arr = arrMkr(br.readLine(), n);

        
            int left = 0 , right = n-1;
     
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == target) {
                    bw.write(String.valueOf(left+1)+" "+String.valueOf(right+1));
                    bw.flush();
                    return;
                    
                }
                else if (sum < target) {
                    left++;
                    
                }else{
                    right--;
                }
                
            }
       
                bw.write(String.valueOf(-1));
           
       
     
            bw.flush();
            br.close();
            bw.close();
        }
     
        static int[] arrMkr(String s, int n){
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(s);
            for (int i = 0; i < n; i++) 
                arr[i] = Integer.parseInt(st.nextToken());
     
            return arr;
        }
     
    }