import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int M,N; // 가로칸 수, 세로칸 수
    static int[][] storage;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        storage = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                storage[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        bfs();
        int max = 1;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(storage[i][j]==0){
                    bw.write("-1");
                    bw.flush();
                    return;
                }
                else if(storage[i][j]>max){
                    max = storage[i][j];
                }
            }
        }

        bw.write(Integer.toString(max-1));
        bw.flush();
    }

    public static void bfs(){
//        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();


        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(storage[i][j]==1){
                    visited[i][j] = true;
                    queue.add(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()){
            int[] now = queue.remove();
            int nowX = now[0];
            int nowY = now[1];

            for(int i=0;i<4;i++){
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];

                if(newX>=0 && newX<N && newY>=0 && newY<M){
                    if(!visited[newX][newY] && storage[newX][newY]==0){
                        visited[newX][newY] = true;
                        storage[newX][newY] = storage[nowX][nowY]+1;
                        queue.add(new int[]{newX,newY});
                    }
                }
            }
        }
    }
}
