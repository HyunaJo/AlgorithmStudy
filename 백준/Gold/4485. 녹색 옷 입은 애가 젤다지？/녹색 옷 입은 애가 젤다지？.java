import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,t;
    static int[][] map;
    static int[][] minMoney;
    static int min;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static StringBuilder sb = new StringBuilder();

    static class Location implements Comparable<Location>{
        int x;
        int y;
        int sum;

        public Location(int x, int y, int sum){
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        @Override
        public int compareTo(Location o) {
            return this.sum - o.sum;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = 1;
        while((N= Integer.parseInt(br.readLine()))!=0){
            map = new int[N][N];
            minMoney = new int[N][N];
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    minMoney[i][j] = Integer.MAX_VALUE;
                }
            }

            min = Integer.MAX_VALUE;
            findMin();

            sb.append("Problem ").append(t).append(": ").append(findMin()).append("\n");
            t++;
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static int findMin(){
        PriorityQueue<Location> pq = new PriorityQueue<>();
        pq.add(new Location(0,0, map[0][0]));
        minMoney[0][0] = map[0][0];

        while(!pq.isEmpty()){
            Location now = pq.poll();

            if(now.x == N-1 && now.y == N-1){
                return now.sum;
            }

            if(minMoney[now.x][now.y] < now.sum){
                continue;
            }

            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=N){
                    continue;
                }

                if(minMoney[nx][ny] <= now.sum+map[nx][ny]){
                    continue;
                }

                minMoney[nx][ny] = now.sum+map[nx][ny];
                pq.add(new Location(nx,ny,minMoney[nx][ny]));
            }
        }

        return minMoney[N-1][N-1];
    }
}
