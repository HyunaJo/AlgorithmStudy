import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_VALUE = 200_000_000;
    static int N,E;
    static ArrayList<Edge>[] edges;
    static int v1, v2;
    static int[] minCost;

    static class Edge implements Comparable<Edge>{
        int to;
        int cost;

        public Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            edges[i] = new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b,cost));
            edges[b].add(new Edge(a,cost));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        minCost = new int[N+1];
        // 1 -> v1 -> v2 -> N
        int ans1 = getCost(v1,v2);

        // 1 -> v2 -> v1 -> N
        int ans2 = getCost(v2,v1);

        bw.write((ans1>=MAX_VALUE && ans2>=MAX_VALUE)?"-1":Integer.toString(Math.min(ans1,ans2)));
        bw.flush();
    }

    public static int getCost(int first, int second){
        return findMin(1,first) + findMin(first,second) + findMin(second,N);
    }

    public static int findMin(int start, int end){
        Arrays.fill(minCost, MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));
        minCost[start] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.to == end){
                return now.cost;
            }
            ArrayList<Edge> nexts = edges[now.to];
            for(Edge next:nexts){
                if(minCost[next.to] <= now.cost + next.cost){
                    continue;
                }

                minCost[next.to] = now.cost + next.cost;
                Edge edge = new Edge(next.to, minCost[next.to]);
                pq.add(edge);
            }
        }

        return MAX_VALUE;
    }
}
