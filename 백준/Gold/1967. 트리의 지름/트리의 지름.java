import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Edge>[] edges;
    static boolean[] visited;
    static int ans;

    static class Edge{
        int end;
        int w;

        public Edge(int end, int w){
            this.end = end;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        edges = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=0;i<n-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[parent].add(new Edge(child,w));
            edges[child].add(new Edge(parent,w));
        }

        ans = 0;
        for(int i=1;i<=n;i++){
            visited = new boolean[n+1];
            findMax(i, 0);
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    public static void findMax(int idx, int sum){
        ans = Math.max(ans, sum);
        visited[idx] = true;

        for(Edge edge:edges[idx]){
            if(visited[edge.end]){
                continue;
            }

            findMax(edge.end, sum+edge.w);
        }
    }
}
