import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V,E;
    static ArrayList<Edge>[] edges;
    static int[] minWeights;

    static class Edge implements Comparable<Edge>{
        int to;
        int weight;

        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight,o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new ArrayList[V+1];
        minWeights = new int[V+1];
        for(int i=1;i<=V;i++){
            edges[i] = new ArrayList<>();
            minWeights[i] = Integer.MAX_VALUE;
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to,weight));
            edges[to].add(new Edge(from,weight));
        }

        bw.write(Integer.toString(findMin()));
        bw.flush();

        br.close();
        bw.close();
    }

    public static int findMin(){
        boolean[] visited = new boolean[V+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,0));
        minWeights[1] = 0;

        int cnt = 0;
        int sum = 0;
        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(visited[now.to]){
                continue;
            }

            visited[now.to] = true;
            sum += now.weight;
            if(++cnt == V){
                break;
            }

            ArrayList<Edge> nexts = edges[now.to];
            for(Edge next:nexts){
                if(visited[next.to]){
                    continue;
                }

                if(minWeights[next.to] <= next.weight){
                    continue;
                }

                minWeights[next.to] = next.weight;
                pq.add(new Edge(next.to, next.weight));
            }
        }

        return sum;
    }
}


