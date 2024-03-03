import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final int BLANK = 0;
    static final int OBSTACLE = 1;

    static int K,W,H;
    static int[][] map;
    static int[][][] movings;
    static int[][] horseDirections = {{-2,-1},{-1,-2},{-2,1},{-1,2},{2,-1},{1,-2},{2,1},{1,2}};
    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    static class Location{
        int x;
        int y;
        int k;
        int movingCnt;

        public Location(int x, int y, int k, int movingCnt){
            this.x = x;
            this.y = y;
            this.k = k;
            this.movingCnt = movingCnt;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for(int i=0;i<H;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        movings = new int[H][W][K+1];
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                Arrays.fill(movings[i][j],Integer.MAX_VALUE);
            }
        }
        System.out.println(findMin());
    }

    public static int findMin(){
        if(W==1 && H==1){
            return 0;
        }

        Deque<Location> dq = new ArrayDeque<>();
        dq.add(new Location(0,0,0,0));

        while(!dq.isEmpty()){
            Location now = dq.poll();
            if(now.x==H-1 && now.y==W-1){
                return now.movingCnt;
            }

            if(now.k < K){
                for(int i=0;i<8;i++){
                    int nx = now.x + horseDirections[i][0];
                    int ny = now.y + horseDirections[i][1];

                    if(!isPossible(nx,ny)){
                        continue;
                    }

                    if(movings[nx][ny][now.k+1] <= now.movingCnt+1){
                        continue;
                    }

                    movings[nx][ny][now.k+1] = now.movingCnt+1;
                    dq.add(new Location(nx, ny,now.k+1,now.movingCnt+1));
                }
            }

            for(int i=0;i<4;i++){
                int nx = now.x + directions[i][0];
                int ny = now.y + directions[i][1];

                if(!isPossible(nx,ny)){
                    continue;
                }

                if(movings[nx][ny][now.k] <= now.movingCnt+1){
                    continue;
                }

                movings[nx][ny][now.k] = now.movingCnt+1;
                dq.add(new Location(nx, ny,now.k,now.movingCnt+1));
            }

        }

        return -1;
    }

    public static boolean isPossible(int nx, int ny){
        if(nx<0 || nx>=H || ny<0 || ny>=W){
            return false;
        }

        if(map[nx][ny] == OBSTACLE){
            return false;
        }

        return true;
    }
}
