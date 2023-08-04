import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static char[] input1;
    public static char[] input2;
    public static int[][] dp;
    public static int ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input1 = br.readLine().toCharArray();
        input2 = br.readLine().toCharArray();

        int input1Len = input1.length;
        int input2Len = input2.length;
        dp = new int[input2Len+1][input1Len+1];

        for(int i=1;i<=input2Len;i++){
            for(int j=1;j<=input1Len;j++){
                if(input2[i-1]!=input1[j-1])
                    continue;

                int cnt = dp[i-1][j-1]+1;
                dp[i][j] = cnt;
                ans = Math.max(ans,cnt);
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        
        br.close();
        bw.close();
    }
}
