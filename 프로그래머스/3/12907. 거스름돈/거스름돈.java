import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int moneyCnt = money.length;
        int[][] dp = new int[moneyCnt][n+1];
        
        Arrays.sort(money);
        for(int j=1;j<=n;j++){
            if(j % money[0] == 0){
                dp[0][j] = 1;
            }
        }
        
        for(int i=1;i<moneyCnt;i++){
            dp[i][0] = 1;
            for(int j=1;j<=n;j++){
                if(money[i] == j){
                    dp[i][j] = dp[i-1][j] + 1;
                }
                else if (money[i] < j){
                    dp[i][j] = dp[i-1][j] + dp[i][j-money[i]];
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[moneyCnt-1][n];
    }
}