import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    static int T,N,W,H;
    static int[][] map;
    static int totalBrickCnt;
    static int answer;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static StringBuilder sb = new StringBuilder();

    static class Location{
        int x;
        int y;
        int num;

        public Location(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            totalBrickCnt = 0;
            for(int i=0;i<H;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<W;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(map[i][j] != 0){
                        totalBrickCnt++;
                    }
                }
            }

            answer = totalBrickCnt;
            play(map, 0);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void play(int[][] changedMap, int cnt){
        if(cnt == N || totalBrickCnt == 0){
            answer = Math.min(answer, totalBrickCnt);
            return;
        }

        for(int j=0;j<W;j++){
            int row = findRow(changedMap, j);
            if(row == -1){
                continue;
            }

            int[][] copiedMap = copyMap(changedMap);
            int removedCnt = breakBrick(copiedMap, row, j);
            applyGravity(copiedMap);
            totalBrickCnt -= removedCnt;
            play(copiedMap, cnt+1);
            totalBrickCnt += removedCnt;
        }
    }

    public static void print(int[][] copiedMap){
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                System.out.print(copiedMap[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    public static int[][] copyMap(int[][] map){
        int[][] copiedMap = new int[H][W];
        for(int i=0;i<H;i++){
            copiedMap[i] = Arrays.copyOf(map[i], W);
        }

        return copiedMap;
    }

    public static int findRow(int[][] changedMap, int col){
        for(int i=0;i<H;i++){
            if(changedMap[i][col] != 0){
                return i;
            }
        }

        return -1;
    }

    public static int breakBrick(int[][] copiedMap, int x, int y){
        Deque<Location> dq = new ArrayDeque<>();
        dq.add(new Location(x,y, copiedMap[x][y]));

        copiedMap[x][y] = 0;
        int removedCnt = 1;
        while(!dq.isEmpty()){
            Location now = dq.poll();
            int num = now.num;

            for(int i=0;i<4;i++){
                int nx = now.x;
                int ny = now.y;

                for(int j=1;j<num;j++){
                    nx += dx[i];
                    ny += dy[i];

                    if(isOutOfMap(nx,ny)){
                        break;
                    }

                    if(copiedMap[nx][ny] == 0){
                        continue;
                    }

                    dq.add(new Location(nx,ny,copiedMap[nx][ny]));
                    copiedMap[nx][ny] = 0;
                    removedCnt++;
                }
            }
        }

        return removedCnt;
    }

    public static void applyGravity(int[][] copiedMap){
        for(int j=0;j<W;j++){
            int blankCnt = 0;
            for(int i=H-1;i>=0;i--){
                if(copiedMap[i][j] == 0){
                    blankCnt++;
                    continue;
                }

                if(blankCnt > 0){
                    copiedMap[i+blankCnt][j] = copiedMap[i][j];
                    copiedMap[i][j] = 0;
                }
            }
        }
    }

    public static boolean isOutOfMap(int x, int y){
        return x<0 || x>=H || y<0 || y>=W;
    }
}