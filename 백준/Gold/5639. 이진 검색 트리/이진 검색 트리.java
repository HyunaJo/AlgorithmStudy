import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static Node rootNode;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        rootNode = new Node(Integer.parseInt(br.readLine())); // 루트 노드 설정
        String input = "";
        while((input=br.readLine()) != null && !input.isEmpty()){
            int value = Integer.parseInt(input);

            addNode(value, rootNode);
        }

        postOrder(rootNode);
        bw.flush();

        br.close();
        bw.close();
    }

    public static void addNode(int value, Node node){
        if(node.value > value){ // 왼쪽으로 들어가야 하는 경우
            if(node.left == null){ // 왼쪽에 새로운 노드 삽입
                node.left = new Node(value);
                return;
            }

            addNode(value, node.left);
        }
        else{ // 오른쪽으로 들어가야하는 경우
            if(node.right == null){ // 왼쪽에 새로운 노드 삽입
                node.right = new Node(value);
                return;
            }

            addNode(value, node.right);
        }
    }

    public static void postOrder(Node node) throws Exception{ // 후위 순회
        if(node.left != null){
            postOrder(node.left);
        }

        if(node.right != null){
            postOrder(node.right);
        }

        bw.write(Integer.toString(node.value)+"\n");
    }
}
