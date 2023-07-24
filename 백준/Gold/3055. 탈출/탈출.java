import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int R,C;
    public static char[][] map;
    public static boolean[][] visited;

    public static Queue<Location> waters = new LinkedList<>(); // 물 위치
    public static Queue<Location> sLocs = new LinkedList<>(); // 고슴도치 위치

    public static final String FAIL = "KAKTUS";
    public static int time;

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
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for(int i=0;i<R;i++){
            String input = br.readLine();
            for(int j=0;j<C;j++){
                char inputChar = input.charAt(j);
                if(inputChar == 'S') {
                    visited[i][j] = true;
                    sLocs.add(new Location(i, j));
                }
                else if(inputChar == '*') {
                    visited[i][j] = true;
                    waters.add(new Location(i, j));
                }
                map[i][j] = inputChar;
            }
        }

        bfs();
        if(time == -1)
            bw.write(FAIL);
        else
            bw.write(Integer.toString(time));
        
        bw.flush();
        
        br.close();
        bw.close();
    }

    public static void bfs(){
        for(time=1;;time++){
            // 물 차는 것 확인
            int waterCnt = waters.size();
            for(int i=0;i<waterCnt;i++){
                Location nowWater = waters.remove();

                for(int j=0;j<4;j++){
                    int nx = nowWater.x + dx[j];
                    int ny = nowWater.y + dy[j];

                    if(nx<0 || ny<0 || nx>=R || ny>=C)
                        continue;

                    if(map[nx][ny] == '.'){
                        visited[nx][ny] = true;
                        map[nx][ny] = '*';
                        waters.add(new Location(nx, ny));
                    }
                }
            }

            // 고슴도치 이동
            int sCnt = sLocs.size();
            if(sCnt == 0){
                time = -1;
                return;
            }
            for(int i=0;i<sCnt;i++){
                Location sNow = sLocs.remove();

                for(int j=0;j<4;j++){
                    int nx = sNow.x + dx[j];
                    int ny = sNow.y + dy[j];

                    if(nx<0 || ny<0 || nx>=R || ny>=C)
                        continue;

                    if(map[nx][ny] == 'D')
                        return;
                    if(!visited[nx][ny] && map[nx][ny]=='.'){
                        visited[nx][ny] = true;
                        sLocs.add(new Location(nx, ny));
                    }
                }
            }
        }
    }
}
