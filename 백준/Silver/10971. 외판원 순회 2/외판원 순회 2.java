import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] costs;
    static int[] orders;
    static boolean[] visited;
    static int min;

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

        min = Integer.MAX_VALUE;
        orders = new int[N];
        visited = new boolean[N];
        for(int i=0;i<N;i++){
            visited[i] = true;
            orders[0] = i;
            findMin(1,0);
            visited[i] = false;
        }

        bw.write(Integer.toString(min));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMin(int cnt, int cost){
        if(cnt == N){
            if(costs[orders[cnt-1]][orders[0]] == 0){
                return;
            }
            min = Math.min(min,cost+costs[orders[cnt-1]][orders[0]]);
            return;
        }

        if(cost>=min){
            return;
        }

        for(int i=0;i<N;i++){
            if(visited[i] || costs[orders[cnt-1]][i] == 0){
                continue;
            }

            visited[i] = true;
            orders[cnt] = i;
            findMin(cnt+1, cost+costs[orders[cnt-1]][i]);
            visited[i] = false;
        }
    }
}
