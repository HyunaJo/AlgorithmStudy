import java.util.*;
import java.io.*;

public class Main {
    static int N, M, A, B;
    static ArrayList<Road>[] roads;
    static long timesA[];
    static long timesB[];
    static StringBuilder sb = new StringBuilder();

    static class Road implements Comparable<Road>{
        int to;
        long time;

        public Road(int to, long time){
            this.to = to;
            this.time = time;
        }

        public int compareTo(Road o){
            return Long.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        roads = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            roads[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            roads[a].add(new Road(b,c));
            roads[b].add(new Road(a,c));
        }

        timesA = new long[N+1];
        findMin(A,B,timesA);
        timesB = new long[N+1];
        findMin(B,A,timesB);

        long min = timesA[B];
        ArrayList<Integer> possibleCities = new ArrayList<>();
        for(int i=1;i<=N;i++){
            if(timesA[i]+timesB[i] == min){
                possibleCities.add(i);
            }
        }

        sb.append(possibleCities.size()).append("\n");
        for(int city:possibleCities){
            sb.append(city).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void findMin(int start, int end, long[] times){
        Arrays.fill(times, Long.MAX_VALUE);

        PriorityQueue<Road> pq = new PriorityQueue<>();
        Road road = new Road(start,0);
        pq.add(road);
        times[start] = 0;

        while(!pq.isEmpty()){
            Road now = pq.poll();

            if(times[now.to] < now.time){
                continue;
            }

            for(Road next:roads[now.to]){
                if(times[next.to] <= now.time + next.time){
                    continue;
                }

                times[next.to] = now.time + next.time;
                pq.add(new Road(next.to, times[next.to]));
            }
        }
    }
}
