import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Edge>[] edges;
    static int[] weights;

    static class Edge implements Comparable<Edge>{
        int end;
        int w;

        public Edge(int end, int w){
            this.end = end;
            this.w = w;
        }

        public int compareTo(Edge o){
            return this.w-o.w;
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

        weights = new int[n+1];
        int[] result = findMaxNode(1);
        int[] answer = findMaxNode(result[0]);

        bw.write(Integer.toString(answer[1]));
        bw.flush();
    }

    public static int[] findMaxNode(int start){
        for(int i=1;i<=n;i++){
            weights[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));
        weights[start] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            for(Edge edge:edges[now.end]){
                if(weights[edge.end] <= weights[now.end] + edge.w){
                    continue;
                }

                weights[edge.end] = weights[now.end] + edge.w;
                pq.add(new Edge(edge.end, weights[edge.end]));
            }
        }

        int maxWeight = 0;
        int maxNode = 1;
        for(int i=1;i<=n;i++){
            if(maxWeight >= weights[i]){
                continue;
            }
            maxWeight = weights[i];
            maxNode = i;
        }

        return new int[]{maxNode, maxWeight};
    }
}
