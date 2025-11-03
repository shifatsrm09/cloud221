// import java.io.*;;
// public class T4 {
//     public static void main(String[] args) throws IOException{
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//         int T = Integer.parseInt(br.readLine());

//         for (int i = 0; i < T; i++) {
//              int n = Integer.parseInt(br.readLine());
//              if (n==1) {     bw.write("YES\n"); }
//              else{
                
//             String s = br.readLine();

//             int[] arr = arrMk(s);

//              //check true or false
//             boolean flag = true;

//             for (int j = 0; j < n-1; j++) {
//                 if (arr[j] > arr[j+1]) { flag = false; break; }
                
//             }

//             if (flag) {
//                   bw.write("YES\n");
//             }else{
//                 bw.write("NO\n");
//             }

                

//              }
            

//         }

//         bw.flush();
//         br.close();
//         bw.close();
//     }

//     public static int[] arrMk(String s) {
//         String[] arr = s.split(" ");
//         int[] actualArr = new int[arr.length];
//         for (int i = 0; i < arr.length; i++) {
//            actualArr[i] = Integer.parseInt(arr[i]) ;
//         }
//         return actualArr;
//     }

// }

import java.io.*;

public class T4{
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = arrMkr(br.readLine());
            String verdict = isSorted(arr,n) ? "YES" : "NO";
            bw.write(verdict);
            bw.newLine();
        }
        

        bw.flush();
        br.close();
        bw.close();


    }

    static int[] arrMkr(String s){
        String[] parts = s.split(" ");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i]);
        return arr;
    }

    static boolean isSorted(int[] arr, int n){
        for (int i = 0; i < n-1; i++)  if (arr[i] > arr[i+1]) return false;
        return true;
        }
        
    }
