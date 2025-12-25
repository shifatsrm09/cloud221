import java.io.*;
import java.util.*;
public class B {

     public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //List making
        ArrayList<Integer>[] adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        //Populaating
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);

            
        }
        //color array
        int[] color = new int[N+1];
        Arrays.fill(color, -1);

        //algo
        int result = 0 ;
        for (int i = 1; i <= N; i++) {
           if (color[i] == -1) {
             result += bfscolor(i , color, adjList);
           }
        }

        System.out.println(result);
     }
      
     static int bfscolor(int start , int[] color, ArrayList<Integer>[] adList){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        color[start] = 0;
        int count0 = 1, count1 = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adList[u]) {
                if (color[v] == -1) {
                    color[v] = 1 - color[u];
                    if (color[v] == 0) count0++;
                    else count1++;

                    q.add(v);
                }
                
            }
            
        }

        return Math.max(count0, count1);
     }
    }