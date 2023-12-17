import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int R,C;
    public static char[][] map;
    public static boolean[][] visited;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0;i<R;i++){
            String input = br.readLine();
            map[i] = input.toCharArray();
        }

        visited = new boolean[R][C];
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == 'X'){
                    visited[i][j] = true;
                    changeLand(i,j);
                }
            }
        }

        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minColumn = Integer.MAX_VALUE;
        int maxColumn = Integer.MIN_VALUE;

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == 'X'){
                    minRow = Math.min(minRow,i);
                    maxRow = Math.max(maxRow,i);
                    minColumn = Math.min(minColumn,j);
                    maxColumn = Math.max(maxColumn,j);
                }
            }
        }

        for(int i=minRow;i<=maxRow;i++){
            for(int j=minColumn;j<=maxColumn;j++){
                bw.write(map[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static void changeLand(int x, int y){
        int aroundSeaCnt = 0;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=R || ny<0 || ny>=C){
                aroundSeaCnt++;
            }
            else if(!visited[nx][ny] && map[nx][ny]=='.'){
                aroundSeaCnt++;
            }


        }

        if(aroundSeaCnt >= 3){
            map[x][y] = '.';
        }
    }
}
