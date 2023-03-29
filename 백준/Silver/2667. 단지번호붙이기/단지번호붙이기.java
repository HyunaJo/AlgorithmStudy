import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] map;
    static boolean[][] visited;
    static int N; // 지도의 크기 N
    static ArrayList<Integer> houseNums; // 단지 별 단지내 집 수
    static int houseNum; // 단지내 집 수
    static int complexNum; // 단지 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<N;j++)
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j))); // map 채우기
        }

        houseNums = new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                if(map[i][j]==1 && !visited[i][j]) {
                    houseNum = 0;
                    complexNum++;
                    dfs(i, j);
                    houseNums.add(houseNum);
                }
        }

        bw.write(Integer.toString(complexNum)+"\n");
        Collections.sort(houseNums);
        int i=0;
        for(i=0;i<houseNums.size()-1;i++)
            bw.write(houseNums.get(i)+"\n");
        bw.write(houseNums.get(i)+"\n");
        
        bw.flush();
    }

    public static void dfs(int x, int y){
        visited[x][y] = true;
        houseNum++;

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx>=0 && nx<N && ny>=0 && ny<N)
                if(map[nx][ny]==1 & !visited[nx][ny])
                    dfs(nx,ny);
        }
    }
}
