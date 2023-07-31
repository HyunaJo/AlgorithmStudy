import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static PriorityQueue<Edge> edges = new PriorityQueue<>();
    public static int edgeCnt = 0;
    public static int[] parent;
    public static long ans;

    public static class Edge implements Comparable<Edge>{
        int startId;
        int targetId;
        long cost;

        public Edge(int startId, int targetId, long cost){
            this.startId = startId;
            this.targetId = targetId;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            if(this.cost>o.cost)
                return 1;
            else
                return -1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수

        parent = new int[N+1];
        for(int i=1;i<=N;i++)
            parent[i] = i;

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            edges.add(new Edge(a, b, cost));
        }

        while(!edges.isEmpty()){
            // 탈출 조건 = 모든 정점 연결
            if(edgeCnt==N-1)
                break;

            Edge cur = edges.poll();

            // 1) 이미 연결되어있다면 연결하지 않음
            if(findParent(cur.startId) == findParent(cur.targetId))
                continue;

            // 2) 연결 안되어있으면 연결
            union(cur.startId, cur.targetId);
            ans += cur.cost;
            edgeCnt++;
        }

        bw.write(Long.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void union(int a, int b){
        int pa = findParent(a);
        int pb = findParent(b);

        parent[pa] = pb;
    }

    public static int findParent(int a){
        if(parent[a]==a)
            return a;

        return parent[a] = findParent(parent[a]);
    }
}
