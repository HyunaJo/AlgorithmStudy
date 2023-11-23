import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int moneyCnt = money.length;
        int[][] dp = new int[moneyCnt+1][n+1];
        
        for(int i=1;i<=moneyCnt;i++){
            dp[i][0] = 1;
            for(int j=1;j<=n;j++){
                dp[i][j] = dp[i-1][j];
                if (money[i-1] <= j){
                    dp[i][j] = dp[i-1][j] + dp[i][j-money[i-1]];
                }
                dp[i][j] %= 1_000_000_007;
            }
        }
        
        return dp[moneyCnt][n];
    }
}