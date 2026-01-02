import java.io.*;
import java.util.*;
public class A {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()) , M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)    adjList[i] = new ArrayList<>();

        int[] indegree = new int[N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) , b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            indegree[b]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i]==0) {
                q.add(i);
                
            }
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
           count++;
            sb.append(u).append(" ");
            
            for (int v : adjList[u]) {
                indegree[v]--;
                if (indegree[v]==0) {
                    q.add(v);
                    
                }
                
            }
        }

        System.out.println("Hello World");


        if (count == N) {
            System.out.println(sb.toString());
        }else{
            System.out.println(-1);
        }

        br.close();

    }
}