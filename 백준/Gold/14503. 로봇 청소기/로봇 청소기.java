import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N,M,d;
    static int[][] room;
    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    static int startX, startY;
    static int robotDirection;
    static int cleaningCnt;
    static int dirtyCnt = 0;

    static class Location{
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

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        robotDirection = Integer.parseInt(st.nextToken());

        room = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int status = Integer.parseInt(st.nextToken());
                room[i][j] = status;
                if(status == 0){
                    dirtyCnt++;
                }
            }
        }

        cleaningCnt = 0;
        cleanRoom();
        bw.write(Integer.toString(cleaningCnt));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void cleanRoom(){
        int x = startX;
        int y = startY;

        if(room[x][y] == 0){
            room[x][y] = 2;
            cleaningCnt++;
            dirtyCnt--;
        }

        // 청소 안함 = 0 , 벽 = 1;
        while(dirtyCnt>0){
            if(checkAroundClean(x, y)){ // 주변 4칸 모두 청소된 경우
                int backDirection = (robotDirection+2)%4; // 후진 방향

                int backX = x + directions[backDirection][0];
                int backY = y + directions[backDirection][1];
                if(room[backX][backY] == 1){ // 후진 불가능한 경우
                    return; // 작동 중지
                }

                x = backX;
                y = backY;
                continue;
            }

            robotDirection = (robotDirection+3)%4;
            int nx = x + directions[robotDirection][0];
            int ny = y + directions[robotDirection][1];
            if(isOverRoom(nx,ny) || room[nx][ny] == 1){
                continue;
            }

            if(room[nx][ny] == 2){
                continue;
            }

            room[nx][ny] = 2;
            cleaningCnt++;
            dirtyCnt--;
            x = nx;
            y = ny;
        }
    }

    public static boolean checkAroundClean(int x, int y){
        for(int i=0;i<4;i++){
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];

            if(room[nx][ny]==1){
                continue;
            }

            if(room[nx][ny] == 0){
                return false;
            }
        }

        return true;
    }

    public static boolean isOverRoom(int x, int y){
        return (x<0 || x>=N || y<0 || y>=M);
    }
}
