import java.io.*;
import java.util.*;
public class BP {

     public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjList[u].add(v);
            adjList[v].add(u);
            
        }
        int[] color = new int[N+1];
        for (int i = 0; i <= N; i++) {
            color[i] = -1;
            
        }

        int result = 0;

        for (int i = 1; i <= N; i++) {
            if (color[i] == -1) {
                 result += bfsMax(i,adjList,color);
            }
           
            
        }
        bw.write(String.valueOf(result));
            


        bw.flush();
     }

    static int bfsMax(int start , ArrayList<Integer>[] adjList, int[]color){
         ArrayDeque<Integer> q =  new ArrayDeque<>();
         q.add(start);
         color[start] = 0;
         
         int count0 = 1 , count1 = 0;

         while (!q.isEmpty()) {
            int u = q.poll();
            
            for (int v : adjList[u]) {
                if (color[v] ==  -1) {
                    color[v] =  1 - color[u];

                    if (color[v] == 0)  count0++;
                    else count1++;

                    q.add(v);
                        
                    
                }
                
            }
            
         }
         return Math.max(count0, count1);
     }
    
    }