import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V;
    static ArrayList<Edge>[] edges;

    static class Edge implements Comparable<Edge>{
        int end;
        int w;

        public Edge(int end, int w){
            this.end = end;
            this.w = w;
        }

        public int compareTo(Edge o){
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        V = Integer.parseInt(br.readLine());
        edges = new ArrayList[V+1];
        for(int i=1;i<=V;i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=1;i<=V;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while(true){
                int end = Integer.parseInt(st.nextToken());
                if(end == -1){
                    break;
                }

                int w = Integer.parseInt(st.nextToken());
                edges[node].add(new Edge(end,w));
                edges[end].add(new Edge(node,w));
            }
        }

        int[] result = dijkstra(1);
        int[] answer = dijkstra(result[0]);
        bw.write(Integer.toString(answer[1]));
        bw.flush();
    }

    public static int[] dijkstra(int start){
        int[] minDist = new int[V+1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));
        minDist[start] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            for(Edge edge:edges[now.end]){
                if(minDist[edge.end] <= now.w+ edge.w){
                    continue;
                }

                minDist[edge.end] = now.w + edge.w;
                pq.add(new Edge(edge.end, minDist[edge.end]));
            }
        }

        int node = 0;
        int maxDist = 0;
        for(int i=1;i<=V;i++){
            if(maxDist >= minDist[i]){
                continue;
            }

            maxDist = minDist[i];
            node = i;
        }

        return new int[]{node, maxDist};
    }
}
