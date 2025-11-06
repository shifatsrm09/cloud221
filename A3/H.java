import java.io.*;
import java.util.*;

public class H {
    static int postIndex;                
    static int[] inorder, postorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        inorder = new int[n];
        postorder = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) inorder[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) postorder[i] = Integer.parseInt(st.nextToken());

        postIndex = n - 1;
        preorder(0, n - 1);
        System.out.println(sb.toString().trim());
    }

    // Build PREORDER from INORDER + POSTORDER using a single method
    static void preorder(int l, int r) {
        if (l > r) return;

        int root = postorder[postIndex--];   // last in postorder = root
        sb.append(root).append(' ');

        // find root in inorder
        int m = l;
        while (inorder[m] != root) m++;

        int rightSize = r - m;               // #nodes in right subtree
        int saved = postIndex;               // currently at end of right-subtree block

        // Move postIndex to end of LEFT block (skip the right block temporarily)
        postIndex = saved - rightSize;

        // Process LEFT first (for preorder Root–Left–Right)
        preorder(l, m - 1);

        // Restore to end of RIGHT block and process RIGHT
        postIndex = saved;
        preorder(m + 1, r);
    }
}
