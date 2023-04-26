import java.io.*;

public class Main {
    static char[][] map;
    static boolean visited[][];
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<N;j++)
                map[i][j] = line.charAt(j);
        }

        int originalCnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                if(!visited[i][j]){
                    dfs(i,j,map[i][j]);
                    originalCnt++;
                }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                if(map[i][j]=='G')
                    map[i][j]='R';
        }
        visited = new boolean[N][N];

        int changedCnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                if(!visited[i][j]){
                    dfs(i,j,map[i][j]);
                    changedCnt++;
                }
        }

        bw.write(Integer.toString(originalCnt)+" "+Integer.toString(changedCnt));
        bw.flush();
    }

    public static void dfs(int x, int y, char color){
        visited[x][y] = true;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && nx<N && ny>=0 && ny<N){
                if(!visited[nx][ny] && map[nx][ny]==color)
                    dfs(nx,ny,color);
            }
        }
    }
}
