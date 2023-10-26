import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N; // 아이들의 수
    public static int[] nums; // 현재 서있는 순서
    public static int[] dp; // 증가하는 수열의 길이

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new int[N];
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(br.readLine());
            dp[i] = 1;
        }

        int maxCnt = 0;
        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                if(nums[i]<nums[j]){
                    dp[j] = Math.max(dp[j], dp[i]+1);
                    maxCnt = Math.max(maxCnt, dp[j]);
                }
            }
        }

        bw.write(Integer.toString(N-maxCnt));
        bw.flush();

        br.close();
        bw.close();
    }
}
