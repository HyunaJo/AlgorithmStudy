import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final char BLANK = '.';
    static final char BUILDING = 'x';

    static int R,C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1};
    static int[] dy = {1,1,1};
    static int ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0;i<R;i++){
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean[R][C];
        for(int i=0;i<R;i++){
            if(map[i][1] == BLANK){
                findPossibleCnt(i,1);
            }
        }


        bw.write(Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }

    public static boolean findPossibleCnt(int x, int y){
        visited[x][y] = true;

        if(y == C-2){
            ans++;
            return true;
        }

        for(int i=0;i<3;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=R){
                continue;
            }

            if(visited[nx][ny] || map[nx][ny] == BUILDING){
                continue;
            }

            if(findPossibleCnt(nx,ny)){
                return true;
            }
        }

        return false;
    }
}

