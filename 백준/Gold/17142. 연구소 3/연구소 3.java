import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int N, M;
    public static int[][] map;
    public static boolean[][] visited;

    public static ArrayList<Location> viruses = new ArrayList<>();
    public static Location[] activatedVirus;
    public static int blankCnt = 0;
    public static final int INF = 1_000_000_000;
    public static int answer = INF;

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
        N = Integer.parseInt(st.nextToken()); // 연구소의 크기
        M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스 개수

        map = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int type = Integer.parseInt(st.nextToken());

                map[i][j] = type;
                if(type == 2){ // 바이러스인 경우
                    viruses.add(new Location(i,j));
                }
                else if(type == 0){ // 빈칸인 경우
                    blankCnt++;
                }
            }
        }

        if(blankCnt == 0){
            bw.write("0");
            bw.flush();

            br.close();
            bw.close();
            return;
        }

        activatedVirus = new Location[M];
        activateVirus(0,0);

        if(answer == INF)
            bw.write("-1");
        else
            bw.write(Integer.toString(answer));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void activateVirus(int virusIdx, int activatedCnt){
        // 바이러스 활성화
        if(activatedCnt == M){
            bfs();
            return;
        }

        for(int i=virusIdx;i<viruses.size();i++){
            activatedVirus[activatedCnt] = viruses.get(i);
            activateVirus(i+1, activatedCnt+1);
        }
    }

    public static void bfs(){
        Deque<Location> dq = new ArrayDeque<>();
        visited = new boolean[N+1][N+1];

        for(int i=0;i<M;i++){
            Location virus = activatedVirus[i];

            visited[virus.x][virus.y] = true;
            dq.add(virus);
        }

        int time = 0;
        int restBlankCnt = blankCnt;
        while(!dq.isEmpty()){
            int dqSize = dq.size();

            for(int j=0;j<dqSize;j++){
                Location now = dq.poll();

                for(int i=0;i<4;i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(nx<1 || ny<1 || nx>N || ny>N) // 좌표 밖인 경우
                        continue;

                    if(map[nx][ny] == 1 || visited[nx][ny]) // 벽이거나 방문한 경우
                        continue;

                    if(map[nx][ny] == 0)
                        restBlankCnt--;

                    dq.add(new Location(nx,ny));
                    visited[nx][ny] = true;
                }
            }

            time++;

            if(time==answer) // answer와 같은 경우
                return; // 시간이 더 걸리거나 같게 걸리므로 리턴

            if(restBlankCnt == 0) {
                answer = time;
                return;
            }
        }
    }
}
