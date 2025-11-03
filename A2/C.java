import java.io.*;
import java.util.*;

public class C {
    static class Pair {
        int val, idx;
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        
        Pair[] arr = new Pair[n];
        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(Integer.parseInt(st.nextToken()), i+1);
        }

        // bubbleOrder(arr, n);
        Arrays.sort(arr, (a,b) -> Integer.compare(a.val, b.val) );

      
        for (int i = 0; i < n; i++) {
            
            int start = 0 , end = n-1;
            while (start < end) {

                if(start == i ) {start++; continue; }
                if(end == i ) {end--; continue; }

                int sum = arr[i].val + arr[start].val + arr[end].val;

                if (sum == target) {
                    bw.write((arr[i].idx)+ " "+ arr[start].idx+ " "+arr[end].idx);
                    bw.flush();
                    return;
                }else if (sum < target) {
                    start++;
                }else{
                    end--;
                }
            }

       
        }
        
        bw.write(String.valueOf(-1));
  
        bw.flush();
        br.close();
        bw.close();
    }

    static void bubbleOrder(Pair[] arr, int n){
        
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j].val > arr[j+1].val) {
                    Pair temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

               }
            }
            
        }
    }


 }
