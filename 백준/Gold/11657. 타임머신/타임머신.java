import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N,M;
    public static long[] dist;
    public static Edge[] edgeList;
    public static final int INF = 1_000_000_000;
    public static boolean infFlag = false;

    public static class Edge{
        int startId, targetId, cost;

        public Edge(int startId, int targetId, int cost){
            this.startId = startId;
            this.targetId = targetId;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeList = new Edge[M+1];
        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        dist = new long[N+1];
        dist[1] = 0;
        for(int i=2;i<=N;i++)
            dist[i] = INF;

        BellmanFord();
        if(infFlag)
            bw.write("-1");
        else{
            for(int i=2;i<=N;i++){
                if(dist[i] == INF)
                    bw.write("-1\n");
                else
                    bw.write(Long.toString(dist[i])+"\n");
            }
        }


        bw.flush();
        br.close();
        bw.close();
    }

    static void BellmanFord() {
        // 1. N - 1번 동안 간선 M을 모두 확인하기
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                Edge now = edgeList[j];

                // 1-1. 출발지가 현재 무한대이면 continue
                if (dist[ now.startId ] == INF)
                    continue;
                // 1-2. 최솟값으로 값 갱신 가능하면 갱신
                dist[ now.targetId ] = Math.min(dist[ now.targetId ], dist[ now.startId ] + now.cost);
            }
        }

        // 2. 마지막으로 간선 M을 모두 확인해서 갱신이 발생하면 무한루프
        for (int j = 1; j <= M; j++) {
            Edge now = edgeList[j];

            // 2-1. 현재가 무한대이면 continue
            if (dist[ now.startId ] == INF) continue;

            // 2-2. 갱신이 발생한다면 무한루프에 빠질 수 있음
            if (dist[ now.startId ] + now.cost < dist[now.targetId ]) {
                infFlag = true;
                return;
            }
        }

    }
}
