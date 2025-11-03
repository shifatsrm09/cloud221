import java.io.*;
import java.util.*;

public class ReverseSorting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        String[] tokens = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                sorted = false;
                break;
            }
        }
        
        if (sorted) {
            bw.write("YES\n");
            bw.write("0\n");
            bw.flush();
            return;
        }
        
        // For n < 3, we can't perform any operations
        if (n < 3) {
            bw.write("NO\n");
            bw.flush();
            return;
        }
        
        // Create a copy for manipulation
        int[] workingArr = Arrays.copyOf(arr, n);
        List<String> operations = new ArrayList<>();
        
        // Use bubble sort approach with 3-element reversals
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 2; j++) {
                // If the element at j+2 is smaller than element at j, reverse to swap them
                if (workingArr[j] > workingArr[j + 2]) {
                    reverseThree(workingArr, j);
                    operations.add((j + 1) + " " + (j + 3));
                }
            }
        }
        
        // Check if sorted after operations
        sorted = true;
        for (int i = 1; i < n; i++) {
            if (workingArr[i] < workingArr[i - 1]) {
                sorted = false;
                break;
            }
        }
        
        if (sorted) {
            bw.write("YES\n");
            bw.write(operations.size() + "\n");
            for (String op : operations) {
                bw.write(op + "\n");
            }
        } else {
            bw.write("NO\n");
        }
        
        bw.flush();
    }
    
    private static void reverseThree(int[] arr, int start) {
        int temp = arr[start];
        arr[start] = arr[start + 2];
        arr[start + 2] = temp;
    }
}