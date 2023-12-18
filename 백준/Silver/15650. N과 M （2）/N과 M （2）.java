import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N,M;
    public static int[] nums;
    public static boolean[] visited;
    public static BufferedWriter bw;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[M];
        visited = new boolean[N+1];
        for(int i=1;i<=N;i++){
            nums[0] = i;
            visited[i] = true;
            findArray(i,1);
            visited[i] = false;
        }
        bw.flush();
    }

    public static void findArray(int num, int cnt) throws Exception {
        if(cnt == M){
            for(int i=0;i<cnt;i++){
                bw.write(nums[i]+" ");
            }
            bw.write("\n");
            return;
        }

        for(int i=num+1;i<=N;i++){
            if(!visited[i]){
                nums[cnt] = i;
                visited[i] = true;
                findArray(i,cnt+1);
                visited[i] = false;
            }
        }
    }
}
