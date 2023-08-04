import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static int R,C; // 행, 열
    public static char[][] map;
    public static boolean[][] visited;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int time = 0;

    public static Deque<Location> sDeque = new ArrayDeque<>(); // 고슴도치 위치
    public static Deque<Location> wDeque = new ArrayDeque<>(); // 물 위치

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
                map[i][j] = inputChar;
                if(inputChar=='S'){
                    visited[i][j] = true;
                    sDeque.add(new Location(i,j));
                }
                else if(inputChar=='*'){
                    wDeque.add(new Location(i,j));
                }
            }
        }

        bfs();
        if(time == -1)
            bw.write("KAKTUS");
        else
            bw.write(Integer.toString(time));

        bw.flush();

        br.close();
        bw.close();

    }

    public static void bfs(){
        while(!sDeque.isEmpty()) {
            time++;
            // 물 4방향 퍼짐
            int wDequeSize= wDeque.size();
            for(int j=0;j<wDequeSize;j++){
                Location nowWater = wDeque.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = nowWater.x + dx[i];
                    int ny = nowWater.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= R || ny >= C)
                        continue;

                    if (map[nx][ny] == '.') { // 비어있는 곳인 경우
                        visited[nx][ny] = true;
                        map[nx][ny] = '*'; // 물로 변경시켜주기
                        wDeque.add(new Location(nx, ny));
                    }
                }
            }

            // 고슴도치 이동
            int sDequeSize = sDeque.size();
            for(int j=0;j<sDequeSize;j++){
                Location nowS = sDeque.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = nowS.x + dx[i];
                    int ny = nowS.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= R || ny >= C)
                        continue;

                    if (map[nx][ny] == 'D') {
                        return;
                    }

                    if (map[nx][ny] == '.' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        sDeque.add(new Location(nx, ny));
                    }
                }
            }
        }

        time = -1;
    }
}
