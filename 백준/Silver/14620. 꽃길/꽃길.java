import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[][] costs;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    public static boolean[][] visited;
    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        for(int i=1;i<N-1;i++){
            for(int j=1;j<N-1;j++){
                changeVisited(i,j,true);
                findMin(i,j,getCost(i,j),1);
                changeVisited(i,j,false);
            }
        }
        bw.write(Integer.toString(ans));
        bw.flush();
        
        br.close();
        bw.close();
    }

    public static void findMin(int x, int y, int cost, int cnt){
        if(cnt == 3){
            ans = Math.min(ans, cost);
            return;
        }

        for(int j=y+1;j<N-1;j++){
            if(!isPossible(x,j)){
                continue;
            }

            int newCost = getCost(x,j);
            if(cost+newCost > ans){
                continue;
            }

            changeVisited(x,j,true);
            findMin(x,j,cost+newCost, cnt+1);
            changeVisited(x,j,false);
        }

        for(int i=x+1;i<N-1;i++){
            for(int j=1;j<N-1;j++){
                if(!isPossible(i,j)){
                    continue;
                }

                int newCost = getCost(i,j);
                if(cost+newCost > ans){
                    continue;
                }

                changeVisited(i,j,true);
                findMin(i,j,cost+newCost, cnt+1);
                changeVisited(i,j,false);
            }
        }
    }

    public static boolean isPossible(int x, int y){
        if(visited[x][y]){
            return false;
        }

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(visited[nx][ny]){
                return false;
            }
        }

        return true;
    }

    public static void changeVisited(int x, int y, boolean isVisited){
        visited[x][y] = isVisited;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            visited[nx][ny] = isVisited;
        }
    }

    public static int getCost(int x, int y){
        int cost = costs[x][y];
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            cost += costs[nx][ny];
        }

        return cost;
    }
}
