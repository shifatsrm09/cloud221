import java.io.*;
import java.util.*;

public class K {
    static int preIndex;
    static int[] preorder, postorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        preorder = new int[n];
        postorder = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) preorder[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) postorder[i] = Integer.parseInt(st.nextToken());

        preIndex = 0;
        inorder(0, n - 1);
        System.out.println(sb.toString().trim());
    }

    // Build inorder from preorder + postorder (full binary tree assumption)
    static void inorder(int l, int r) {
        if (l > r || preIndex >= preorder.length) return;

        int root = preorder[preIndex++];

        // Leaf node: single element in subtree
        if (l == r) {
            sb.append(root).append(" ");
            return;
        }

        // Next element in preorder is always left child
        int leftRoot = preorder[preIndex];
        
        // Find boundary in postorder where left subtree ends
        int mid = l;
        while (postorder[mid] != leftRoot) mid++;

        // Inorder = Left → Root → Right
        inorder(l, mid);            // Left Subtree
        sb.append(root).append(" "); // Root
        inorder(mid + 1, r - 1);    // Right Subtree
    }
}
