import java.io.*;
import java.util.*;

public class A {
   
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = {5,22,6,1,6,1,6,2,6,1,44,7,12};
        int[] newArr = mergeSort(arr);
        for (int i : newArr) {
            bw.write(i+" ");
            
        }


        bw.flush();
        br.close();
        bw.close();
    }

    static int[] arrMkr(String s, int n){
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(s);
        for (int i = 0; i < arr.length; i++)  arr[i] = Integer.parseInt(st.nextToken());
        return arr;
 
    }

        public static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        
        while (i < a.length) {
            result[k++] = a[i++];
        }
        
        while (j < b.length) {
            result[k++] = b[j++];
        }
        
        return result;
    }
    
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        } else {
            int mid = arr.length / 2;
            int[] a1 = mergeSort(Arrays.copyOfRange(arr, 0, mid));  
            int[] a2 = mergeSort(Arrays.copyOfRange(arr, mid, arr.length)); 
            return merge(a1, a2);
        }
    }
}
