import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> links = new ArrayList<>();
//    static int[][] links;
    static int N;
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 노드의 개수
//        links = new int[N+1][N+1];
        for(int i=0;i<=N;i++)
            links.add(new ArrayList<Integer>());
        parents = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            links.get(a).add(b);
            links.get(b).add(a);
//            links[a][b] = 1;
//            links[b][a] = 1;
        }

        getParent(1,1);
        for(int i=2;i<=N;i++){
            bw.write(Integer.toString(parents[i])+"\n");
        }
        bw.flush();
    }

    public static void getParent(int node, int parent){
        parents[node] = parent;
        visited[node] = true;

        for(int i:links.get(node)){
            if(!visited[i]){
                getParent(i, node);
            }
        }
    }
}
