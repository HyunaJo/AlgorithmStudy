import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[][] map;
    static int[][][] minDist;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static class Moving implements Comparable<Moving>{
        int x;
        int y;
        int breakCnt;
        int movingCnt;

        public Moving(int x, int y, int breakCnt, int movingCnt){
            this.x = x;
            this.y = y;
            this.breakCnt = breakCnt;
            this.movingCnt = movingCnt;
        }

        public int compareTo(Moving o){
            return this.movingCnt - o.movingCnt;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        minDist = new int[N+1][M+1][2];
        for(int i=1;i<=N;i++){
            String input = br.readLine();
            for(int j=1;j<=M;j++){
                map[i][j] = input.charAt(j-1) - '0';
                minDist[i][j][0] = Integer.MAX_VALUE;
                minDist[i][j][1] = Integer.MAX_VALUE;
            }
        }

        bw.write(Integer.toString(findMin()));
        bw.flush();
    }

    public static int findMin(){
        PriorityQueue<Moving> pq = new PriorityQueue<>();
        pq.add(new Moving(1,1,0,1));
        minDist[1][1][0] = 1;

        while(!pq.isEmpty()){
            Moving now = pq.poll();

            if(now.x == N && now.y == M){
                return now.movingCnt;
            }

            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<1 || nx > N || ny<1 || ny > M){
                    continue;
                }

                if(map[nx][ny] == 0){
                    if(minDist[nx][ny][now.breakCnt] <= now.movingCnt + 1){
                        continue;
                    }

                    minDist[nx][ny][now.breakCnt] = now.movingCnt + 1;
                    pq.add(new Moving(nx, ny, now.breakCnt,  minDist[nx][ny][now.breakCnt]));
                    continue;
                }

                if(now.breakCnt == 1 || minDist[nx][ny][1] <= now.movingCnt + 1){
                    continue;
                }

                minDist[nx][ny][1] = now.movingCnt + 1;
                pq.add(new Moving(nx, ny, 1,  minDist[nx][ny][1]));
            }
        }

        return -1;
    }
}
