import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int R,C,N; // 세로 수, 가로 수, N초 후
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0;i<R;i++){
            String row = br.readLine();
            for(int j=0;j<C;j++)
                map[i][j] = row.charAt(j);
        }

        if(N == 1){ // N이 1인 경우
            // 초기값
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++)
                    bw.write(Character.toString(map[i][j]));
                bw.write("\n");
            }
        }
        else if(N%2==0){ // N이 짝수인 경우
            // 폭탄으로 map이 채워져있음
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++)
                    bw.write("O");
                bw.write("\n");
            }
        }
        else{
            // 초기 상태에서 폭탄 터진 경우
            visited = new boolean[R][C];

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]=='O') { // 폭탄인 경우
                        map[i][j] = '.';
                        bombExplode(i, j);
                    }
                }
            }

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++) {
                    if (!visited[i][j])
                        map[i][j] = 'O';
                }
            }

            if((N/2)%2==0) { // N은 홀수이고 N/2가 짝수인 경우 (5,9,13,...)
                // 다시 폭탄 세팅
                visited = new boolean[R][C];

                for(int i=0;i<R;i++){
                    for(int j=0;j<C;j++){
                        if(map[i][j]=='O') { // 폭탄인 경우
                            map[i][j] = '.';
                            bombExplode(i, j);
                        }
                    }
                }
            }

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++) {
                    if (!visited[i][j])
                        bw.write("O");
                    else
                        bw.write(".");
                }
                bw.write("\n");
            }
        }
        bw.flush();
    }

    public static void bombExplode(int x, int y){
        visited[x][y] = true;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && nx<R && ny>=0 && ny<C){
                if(!visited[nx][ny] && map[nx][ny]!='O'){
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
