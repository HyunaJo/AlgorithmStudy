import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int V,E,K;
    public static int[] dist;
    public static ArrayList<Edge>[] adjList;
    public static final int INF = 1_000_000_000;

    public static class Edge implements Comparable<Edge>{
        int targetId;
        int cost;

        public Edge(int targetId, int cost){
            this.targetId = targetId;
            this.cost = cost;
        }

        public int compareTo(Edge o){
            return this.cost-o.cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        adjList = new ArrayList[V+1];
        for(int i=1;i<=V;i++)
            adjList[i] = new ArrayList<>();
        dist = new int[V+1];
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[u].add(new Edge(v, w));
        }

        for(int i=1;i<=V;i++)
            dist[i] = INF;
        dijkstra(K);

        for(int i=1;i<=V;i++){
            if(dist[i] == INF)
                bw.write("INF\n");
            else
                bw.write(Integer.toString(dist[i])+"\n");
        }
        
        bw.flush();
        
        br.close();
        bw.close();
    }

    public static void dijkstra(int startId){
        // 1. 출발지 비용은 0으로 하고 출발지를 pq에 넣고 시작
        dist[startId] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(startId,0));

        // 2. 최소 힙에서 맨 위에 있는 정점 꺼냄
        while(!pq.isEmpty()){
            // pq에서 뽑으면서 알게 된 간선 정보 (cost 누적 비용)
            Edge now = pq.poll();

            // 1) 더 큰 가중치로 도착한 경우 패스
            if(now.cost > dist[now.targetId])
                continue;

            // 2) 현재 위치에 연결된 간선 탐색
            int len = adjList[now.targetId].size();
            for(int i=0;i<len;i++){
                // next는 입력받은 간선정보 (cost 단일비용)
                Edge next = adjList[now.targetId].get(i);

                // 3) cost가 더 작을 때만 갱신하고 PQ에 넣기
                if(dist[next.targetId] > now.cost+next.cost){
                    dist[next.targetId] = now.cost + next.cost;
                    pq.add(new Edge(next.targetId, dist[next.targetId]));
                }
            }

        }
    }
}
