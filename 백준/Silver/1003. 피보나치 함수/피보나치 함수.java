import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int T, N;
    static int[][] counts;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        counts = new int[41][2];
        counts[0][0] = 1;
        counts[1][1] = 1;

        visited = new boolean[41];
        visited[0] = true;
        visited[1] = true;

        T = Integer.parseInt(br.readLine());
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            fibonacci(N);
            sb.append(counts[N][0]).append(" ").append(counts[N][1]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static void fibonacci(int n){
        if(visited[n]){
            return;
        }

        fibonacci(n-2);
        fibonacci(n-1);

        visited[n] = true;
        counts[n][0] = counts[n-1][0]+counts[n-2][0];
        counts[n][1] = counts[n-1][1]+counts[n-2][1];
    }
}
