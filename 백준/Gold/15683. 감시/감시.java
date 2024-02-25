import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static final int BLANK = 0;
    static final int WALL = 6;
    static final int WATCHED = -1;
    static int N,M;
    static int[][] office;
    static int[] dx = {-1,0,1,0}; // 상 우 좌 하
    static int[] dy = {0,1,0,-1}; // 상 우 좌 하
    static int[][][] cctvDirections = {{},{{0},{1},{2},{3}},
                                        {{0,2},{1,3}},
                                        {{0,1},{1,2},{2,3},{3,0}},
                                        {{0,1,2},{1,2,3},{2,3,0},{3,0,1}},
                                        {{0,1,2,3}}};
    static int[] rotateCnts = {0,4,2,4,4,1};
    static ArrayList<CCTV> cctvs;
    static int cctvCnt;
    static int blankCnt = 0;
    static int min;

    static class CCTV{
        int x;
        int y;
        int type;

        public CCTV(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctvs = new ArrayList<>();
        office = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                office[i][j] = Integer.parseInt(st.nextToken());

                if(office[i][j] == BLANK){
                    blankCnt++;
                    continue;
                }

                if(office[i][j]!=WALL){
                    cctvs.add(new CCTV(i,j,office[i][j]));
                }
            }
        }

        min = blankCnt;
        cctvCnt = cctvs.size();
        findVisible(0, office, 0);
        bw.write(Integer.toString(min));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findVisible(int cnt, int[][] map, int watchedCnt){
        if(cnt == cctvCnt){
            min = Math.min(min, blankCnt-watchedCnt);
            return;
        }

        CCTV cctv = cctvs.get(cnt);
        int[][] cctvDirection = cctvDirections[cctv.type];
        int rotateCnt = rotateCnts[cctv.type];
        for(int r=0;r<rotateCnt;r++){
            int[][] changedMap = new int[N][M];
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    changedMap[i][j] = map[i][j];
                }
            }

            int tmpWatchedCnt = 0;
            for(int direction:cctvDirection[r]){
                tmpWatchedCnt += checkWatched(cctv.x, cctv.y, direction, changedMap);
            }

            findVisible(cnt+1, changedMap, watchedCnt+tmpWatchedCnt);
        }

    }

    public static int checkWatched(int x, int y, int direction, int[][] map){
        int watchedCnt = 0;

        while(true){
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(nx<0 || nx>=N || ny<0 || ny>=M){
                break;
            }

            if(map[nx][ny] == WALL){
                break;
            }

            if(map[nx][ny] == BLANK){
                map[nx][ny] = WATCHED;
                watchedCnt++;
            }

            x = nx;
            y = ny;
        }

        return watchedCnt;
    }
}
