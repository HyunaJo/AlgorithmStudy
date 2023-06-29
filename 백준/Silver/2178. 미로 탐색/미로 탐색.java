import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] map;
    static boolean[][] visited;

    static int N, M; // 도착점
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for(int i=1;i<=N;i++){
            String rowStr = br.readLine();
            for(int j=1;j<=M;j++)
                map[i][j] = Integer.parseInt(Character.toString(rowStr.charAt(j-1)));
        }

        searchMaze(1,1);
        bw.write(Integer.toString(map[N][M]));
        bw.flush();
    }

    public static void searchMaze(int x, int y){
        visited[x][y] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x,y});

        while(!queue.isEmpty()){
            int temp[] = queue.remove();
            int nowX = temp[0];
            int nowY = temp[1];

            for(int i=0;i<4;i++){
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];

                if (newX>=1 && newX<=N && newY>=1 && newY<=M){
                    if(!visited[newX][newY] && map[newX][newY]!=0){
                        queue.add(new int[] {newX,newY});
                        map[newX][newY] = map[nowX][nowY]+1;
                        visited[newX][newY] = true;
                    }
                }
            }

        }
    }
}
