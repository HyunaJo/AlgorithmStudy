import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int[][] dp;
    public static int max = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split("");
        String[] input2 = br.readLine().split("");

        dp = new int[input2.length+1][input1.length+1];
        for(int i=0;i<input1.length;i++){
            for(int j=0;j<input2.length;j++){
                if(input1[i].equals(input2[j])){
                    dp[j+1][i+1] = dp[j][i]+1;
                    max = Math.max(dp[j+1][i+1], max);
                }
                else{
                    dp[j+1][i+1] =  Math.max(dp[j+1][i], dp[j][i+1]);
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.flush();
        
        br.close();
        bw.close();
    }
}