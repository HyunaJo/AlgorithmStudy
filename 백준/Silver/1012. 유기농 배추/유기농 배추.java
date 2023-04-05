import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 깊이 우선 탐색 - q1012(유기농 배추)
public class Main {
    static int M; // 가로 길이
    static int N; // 세로 길이
    static int K; // 배추 심어진 위치 개수
    static int[][] map; // 배추 심어져있는 맵
    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수

        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[M][N];
            visited = new boolean[M][N];
            for(int j=0;j<K;j++){
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            }

            int warmCnt = 0; // 배추흰지렁이 수
            for(int m=0;m<M;m++){
                for(int n=0;n<N;n++){
                    if(map[m][n]==1 && !visited[m][n]){
                        warmCnt++;
                        dfs(m,n);
                    }
                }
            }
            bw.write(Integer.toString(warmCnt)+"\n");
        }
        bw.flush();
    }

    public static void dfs(int x, int y){
        visited[x][y] = true;

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx>=0 && nx<M && ny>=0 && ny<N){
                if(map[nx][ny]==1 && !visited[nx][ny])
                    dfs(nx,ny);
            }
        }
    }
}
