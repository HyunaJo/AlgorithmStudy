import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N,M;
    public static int[][] heights;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static boolean[][] changed;
    public static int totalIceberg = 0;

    public static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        heights = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int height = Integer.parseInt(st.nextToken());
                if(height > 0){
                    totalIceberg++;
                    heights[i][j] = height;
                }
            }
        }

        int year = 0;
        int splitCnt = 0;
        changed = new boolean[N][M];
        while(splitCnt < 2 && totalIceberg > 1){
            for(int i=0;i<N;i++){
                Arrays.fill(changed[i],false);
            }

            year++;
            meltIceberg();

            boolean[][] visited = new boolean[N][M];
            splitCnt = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(!visited[i][j] && heights[i][j]!=0){
                        splitCnt++;
                        bfs(i, j, visited);
                    }

                    if(splitCnt>=2){
                        break;
                    }
                }
                if(splitCnt>=2){
                    break;
                }
            }
        }

        if(totalIceberg <= 1){
            bw.write("0");
        }
        else{
            bw.write(Integer.toString(year));
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static void meltIceberg(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(heights[i][j] == 0){
                    continue;
                }

                int zeroCnt = 0;
                for(int k=0;k<4;k++){
                    int newX = i+dx[k];
                    int newY = j+dy[k];

                    if(newX<0 || newX>=N || newY<0 || newY>=M){
                        continue;
                    }

                    if(!changed[newX][newY] && heights[newX][newY]==0){
                        zeroCnt++;
                    }
                }

                changed[i][j] = true;
                heights[i][j] -= zeroCnt;
                if(heights[i][j] <= 0){
                    heights[i][j] = 0;
                    totalIceberg--;
                }
            }
        }
    }

    public static void bfs(int x, int y, boolean[][] visited){
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(x, y));

        while(!queue.isEmpty()){
            Location now = queue.poll();

            for(int i=0;i<4;i++){
                int newX = now.x + dx[i];
                int newY = now.y + dy[i];

                if(newX<0 || newX>=N || newY<0 || newY>=M){
                    continue;
                }

                if(visited[newX][newY]){
                    continue;
                }

                visited[newX][newY] = true;
                if(heights[newX][newY] != 0){
                    queue.add(new Location(newX, newY));
                }
            }
        }
    }
}
