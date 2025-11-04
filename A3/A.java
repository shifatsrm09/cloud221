import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {
    static long inversions = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer inp = new StringTokenizer(br.readLine());
        int[]  Inp_arr = new int[n];
        for (int i = 0; i < n; i++)  Inp_arr[i] = Integer.parseInt(inp.nextToken());
        int[] arr = mergSort(Inp_arr);
        bw.write(String.valueOf(inversions));
        bw.newLine();
        for (int i : arr)  bw.write(i + " ");

            
        
        bw.flush();
        br.close();
        bw.close();
        
    }

    static int[] mergSort(int[] arr){
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length/2;
        int[] a = mergSort(Arrays.copyOfRange(arr, 0, mid));
        int[] b = mergSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(a,b);
    }

    static int[] merge(int[] a, int[] b){
        int[] arr = new int[a.length+b.length];
        int i = 0 , j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i]<= b[j])  arr[k++]  = a[i++];
            else { arr[k++]  = b[j++]; inversions+= a.length - i; }

            
        }
        while (i < a.length)  arr[k++] = a[i++];
        while (j<b.length)  arr[k++]= b[j++];

        return arr;
            
      
    }
}
