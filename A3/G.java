import java.io.*;
import java.util.*;

public class G {
    static int preIndex;
    static int[] inorder, preorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        inorder = new int[n];
        preorder = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) inorder[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) preorder[i] = Integer.parseInt(st.nextToken());

        preIndex = 0;       // reset before use
        sb.setLength(0);    // reset output
        postorder(0, n - 1);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    static void postorder(int left, int right) {
        if (left > right) return;

        int root = preorder[preIndex++];

        int mid = -1;
        for (int i = left; i <= right; i++) {
            if (inorder[i] == root) {
                mid = i;
                break;
            }
        }

        postorder(left, mid - 1);
        postorder(mid + 1, right);
        sb.append(root).append(" ");
    }
}
