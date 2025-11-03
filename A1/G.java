import java.io.*;

public class G {

    static class Student {
        int id;
        int mark;
        int index;

        Student(int id, int mark, int index) {
            this.id = id;
            this.mark = mark;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            Student[] students = readStudents(br, n);

            sortStudents(students);

            int swaps = countMinimumSwaps(students);

            printResults(bw, students, swaps);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    // -------------------- Methods --------------------

    // Read students from input
    static Student[] readStudents(BufferedReader br, int n) throws IOException {
        String[] idStr = br.readLine().split(" ");
        String[] markStr = br.readLine().split(" ");

        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Student(Integer.parseInt(idStr[i]), Integer.parseInt(markStr[i]), i);
        }
        return arr;
    }

    // Sort students by descending mark, ascending ID
    static void sortStudents(Student[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
         

            for (int j = 0; j < n - i - 1; j++) {

                // Case 1: sort by mark (descending)
                if (arr[j].mark < arr[j + 1].mark)  swap(arr, j, j + 1);
            

                // Case 2: marks are equal → sort by id (ascending)
                else if (arr[j].mark == arr[j + 1].mark && arr[j].id > arr[j + 1].id)   swap(arr, j, j + 1);
               
            }

        }
    }


    // Swap two students in array
    static void swap(Student[] arr, int i, int j) {
        Student temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Count minimum swaps using cycle detection
    static int countMinimumSwaps(Student[] arr) {
        int swaps = 0;
        boolean[] visited = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;

            int cycle = 0;
            int j = i;

            // Traverse a cycle
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j].index;
                cycle++;
            }

            // For each cycle, swaps = cycleSize - 1
            if (cycle > 1) swaps += cycle - 1;
        }

        return swaps;
    }


    // Print the results
    static void printResults(BufferedWriter bw, Student[] arr, int swaps) throws IOException {
        bw.write("Minimum swaps: " + swaps + "\n");
        for (Student s : arr) {
            bw.write("ID: " + s.id + " Mark: " + s.mark + "\n");
        }
    }
}
