import java.io.*;
import java.util.*;
public class AP {

     public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N =  Integer.parseInt(st.nextToken());
        int M =  Integer.parseInt(st.nextToken());
        //Array Makings
        ArrayList<Integer>[] adj = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] indegree = new int[N+1];

        //Placing values

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A =  Integer.parseInt(st.nextToken());
            int B =  Integer.parseInt(st.nextToken());

            adj[A].add(B);
            indegree[B]++;
            
        }

        //Add 0 indegree nodes to Q
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <=N ; i++) {
            if (indegree[i]==0) {
                q.add(i);
            }
        }

        //applying kahn algo
        int count = 0;
        StringBuilder result = new StringBuilder();
        while (!q.isEmpty()) {
            int u = q.poll();
            result.append(u).append(" ");
            count++;
            for (int v : adj[u]) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.add(v);
                }
                
            }
            
            
        }
        if (count < N) {
            bw.write(String.valueOf("-1"));
        }else{
        bw.write(String.valueOf(result));
        }

        bw.flush();
     }
}