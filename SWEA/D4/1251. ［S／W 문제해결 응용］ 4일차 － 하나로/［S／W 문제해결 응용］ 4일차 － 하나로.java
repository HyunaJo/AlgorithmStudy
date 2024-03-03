import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int T,N;
    static double E;
    static int[] islandX;
    static int[] islandY;
    static ArrayList<Edge>[] edges;
    static boolean[] selected;
    static double[] minL;
    static double ans;
    static StringBuilder sb = new StringBuilder();

    static class Edge implements Comparable<Edge>{
        int to;
        double L;

        public Edge(int to, double L){
            this.to = to;
            this.L = L;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.L, o.L);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            islandX = new int[N];
            islandY = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                islandX[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                islandY[i] = Integer.parseInt(st.nextToken());
            }
            E = Double.parseDouble(br.readLine());

            edges = new ArrayList[N];
            for(int i=0;i<N;i++){
                edges[i] = new ArrayList<>();
            }
            for(int i=0;i<N-1;i++){
                for(int j=i;j<N;j++){
                    double L = Math.pow(islandX[i]-islandX[j],2) + Math.pow(islandY[i]-islandY[j],2);
                    edges[i].add(new Edge(j,L));
                    edges[j].add(new Edge(i,L));
                }
            }

            selected = new boolean[N];
            minL = new double[N];
            ans = 0;
            Arrays.fill(minL, Double.MAX_VALUE);
            findMin();
            sb.append("#").append(t).append(" ").append(Math.round(ans*E)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMin(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0,0));
        minL[0] = 0;

        int cnt = 0;
        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(selected[now.to]){
                continue;
            }

            ans += now.L;
            selected[now.to] = true;
            if(++cnt == N){
                return;
            }

            ArrayList<Edge> nexts = edges[now.to];
            for(Edge next:nexts){
                if(selected[next.to]){
                    continue;
                }

                if(minL[next.to] <= next.L){
                    continue;
                }

                minL[next.to] = next.L;
                pq.add(new Edge(next.to,next.L));
            }
        }
    }
}
