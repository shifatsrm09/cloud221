import java.io.*;

public class H {

    static class schedule{
        String train;
        String TimeString;
        int departure_time;
        String destination;
        public schedule(String name, String departureString, String destination){
            train = name;
            this.destination = destination;
            TimeString = departureString;
            String[] parts = departureString.split(":");
            departure_time = Integer.parseInt(parts[0]+parts[1]);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        schedule[] schedules = new schedule[T];
        for (int i = 0; i < T; i++) {
            String[] inputTXT = br.readLine().split(" ");
            schedules[i] = new schedule(inputTXT[0], inputTXT[inputTXT.length-1], inputTXT[inputTXT.length-3]);
        }

        sortSchedules(schedules);

        for (int i = 0; i <T; i++) bw.write(schedules[i].train + " will departure for "+ schedules[i].destination+" at "+schedules[i].TimeString+"\n");
            
        

        bw.flush();
        br.close();
        bw.close();

    }

        // --- Manual Lexicographic Sort Method ---
    static void sortSchedules(schedule[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                // Compare lexicographically by train name
                int cmp = arr[j].train.compareTo(arr[j + 1].train);

                if (cmp > 0) {
                    swap(arr, j, j + 1);
                } 
                // If same train name, compare by time
                else if (cmp == 0 && arr[j].departure_time < arr[j + 1].departure_time) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    static void swap(schedule[] arr, int i, int j) {
        schedule temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
