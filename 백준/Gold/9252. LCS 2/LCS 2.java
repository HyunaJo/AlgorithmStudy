import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static char[] input1;
    public static char[] input2;
    public static int[][] dp;

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
                if(input2[i-1]==input1[j-1]){
                    // 같은 글자인 경우
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int lcsLen = dp[input2Len][input1Len];
        bw.write(Integer.toString(lcsLen)+"\n"); // lcs 길이
        if(lcsLen != 0){
            Deque<Character> deque = new ArrayDeque<>();
            bw.write(findAns(input2Len,input1Len));
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static String findAns(int x, int y){
        String ans = "";
        while(dp[x][y]>0){
            if(dp[x-1][y] == dp[x][y]) {
                x = x - 1;
                continue;
            }

            if(dp[x][y-1] == dp[x][y]){
                y = y - 1;
                continue;
            }

            ans = input1[y-1] + ans;
            x = x - 1;
            y = y - 1;
        }

        return ans;
    }
}
