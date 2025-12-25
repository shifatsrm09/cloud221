import java.io.*;
import java.util.*;

public class DP {
        static ArrayList<Integer>[] adjList;
        static boolean visited[];
        static int maxDist;
        static int farNode;
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            //Input & graph building
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st;

            adjList = new ArrayList[N+1];
            for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

            for (int i = 0; i < N-1; i++) {

                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                
                adjList[u].add(v);
                adjList[v].add(u);
            }

            //Find Point A
            visited = new boolean[N+1];
            farNode = 1;
            maxDist = -1;
            dfsFarNode(1,0);
            int A = farNode;


            //Find Point B
            visited = new boolean[N+1];
            maxDist = -1;
            dfsFarNode(A,0);
            int B = farNode;

            //Output

            bw.write(String.valueOf(maxDist));
            bw.newLine();
            bw.write(A + " " + B);
             bw.newLine();



            bw.flush();
            
        }

        static void dfsFarNode(int currentNode , int distance){
            visited[currentNode] = true;

            if (distance > maxDist) {
                maxDist = distance;
                farNode =  currentNode;
                
            }

            for ( int v : adjList[currentNode]) {
                if (!visited[v]) {
                    dfsFarNode(v, distance+1);
                    
                }
                
            }
        }

}
