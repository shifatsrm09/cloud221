import java.io.*;
public class F {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
   
        String s = br.readLine();
        if (n==1) bw.write(s); // handle i size input
        else{

        int[] arr = mkArr(n, s);
           while (swapPossible(arr)) {
                for (int i = 0; i < n-1; i++) {
                 boolean flag = ( ((arr[i] % 2 == 0) && (arr[i+1] % 2 == 0)) || ((arr[i] % 2 != 0)) && (arr[i+1] % 2 != 0) ) && (arr[i]>arr[i+1]) ;
                if (flag)  swap(arr, i);
               }
           }
           
        
   

             // write sorted array
             for (int i = 0; i < arr.length; i++)  bw.write(arr[i]+ " ");
         }


        bw.flush();
        br.close();
        bw.close();

        
    }

    static int[] mkArr(int n, String s){
        int[] arr = new int[n];
        String[] parts = s.split(" ");
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    static void swap(int[] arr, int i){
                int temp = arr[i+1];
                arr[i+1] = arr[i];
                arr[i] = temp;
    }

    static boolean swapPossible(int[] arr){
        boolean flag = false; 
        for (int i = 0; i < arr.length-1; i++) {
            if (( ((arr[i] % 2 == 0) && (arr[i+1] % 2 == 0)) || ((arr[i] % 2 != 0)) && (arr[i+1] % 2 != 0) ) && (arr[i]>arr[i+1]) ) {
                flag = true;
            }
        }
        return flag;
    }
}