import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static ArrayList<Bus>[] buses;
    static int targetStart, targetEnd;
    static int[] minCosts;

    static class Bus implements Comparable<Bus>{
        int end, cost;

        public Bus(int end, int cost){
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 버스 개수

        buses = new ArrayList[N+1];
        minCosts = new int[N+1];
        for(int i=1;i<=N;i++){
            buses[i] = new ArrayList<>();
            minCosts[i] = Integer.MAX_VALUE;
        }

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            buses[start].add(new Bus(end,cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        targetStart = Integer.parseInt(st.nextToken());
        targetEnd = Integer.parseInt(st.nextToken());

        findMin();
        bw.write(Integer.toString(minCosts[targetEnd]));
        bw.flush();
    }

    public static void findMin(){
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.add(new Bus(targetStart,0));
        minCosts[targetStart] = 0;

        while(!pq.isEmpty()){
            Bus now = pq.poll();
            if(now.end == targetEnd){
                return;
            }

            ArrayList<Bus> nexts = buses[now.end];
            for(Bus next:nexts){
                if(minCosts[next.end] <= now.cost + next.cost){
                    continue;
                }

                minCosts[next.end] = now.cost + next.cost;
                pq.add(new Bus(next.end, minCosts[next.end]));
            }
        }
    }
}
