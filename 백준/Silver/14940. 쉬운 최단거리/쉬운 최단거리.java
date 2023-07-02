import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m; // 세로 크기, 가로 크기
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] map;
    static boolean[][] visited;
    static int targetX, targetY; // 목표지점 위치
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 2){
                    targetX = i;
                    targetY = j;
                }
                map[i][j] = num;
            }
        }

        bfs(targetX, targetY);

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]==false && map[i][j]!=0){
                    bw.write("-1 ");
                }
                else{
                    bw.write(Integer.toString(map[i][j])+" ");
                }
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public static void bfs(int x, int y){
        map[x][y] = 0;
        visited[x][y] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});

        while(!queue.isEmpty()){
            int now[] = queue.remove();
            int nowX = now[0];
            int nowY = now[1];

            for(int i=0;i<4;i++) {
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];

                if (newX>=0 && newX<n && newY>=0 && newY<m){
                    if(!visited[newX][newY]){
                        visited[newX][newY] = true;

                        int num = map[newX][newY];
                        if(num!=0){
                            map[newX][newY] = map[nowX][nowY]+1;
                            queue.add(new int[]{newX, newY});
                        }
                    }
                }
            }
        }
    }
}
