import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int N; // 노드의 개수
    public static Node[] nodes;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class Node{
        char left;
        char right;

        public Node(char left, char right){
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            nodes[st.nextToken().charAt(0)-'A'] = new Node(st.nextToken().charAt(0), st.nextToken().charAt(0));
        }

        preorder('A');
        bw.write("\n");
        inorder('A');
        bw.write("\n");
        postOrder('A');
        bw.flush();

        br.close();
        bw.close();
    }

    public static void preorder(char alphabet) throws Exception {
        Node now = nodes[alphabet-'A'];

        bw.write(Character.toString(alphabet));

        if(now.left != '.')
            preorder(now.left);

        if(now.right != '.')
            preorder(now.right);
    }

    public static void inorder(char alphabet) throws Exception {
        Node now = nodes[alphabet-'A'];

        if(now.left != '.')
            inorder(now.left);

        bw.write(Character.toString(alphabet));

        if(now.right != '.')
            inorder(now.right);
    }

    public static void postOrder(char alphabet) throws Exception {
        Node now = nodes[alphabet-'A'];

        if(now.left != '.')
            postOrder(now.left);

        if(now.right != '.')
            postOrder(now.right);

        bw.write(Character.toString(alphabet));
    }
}