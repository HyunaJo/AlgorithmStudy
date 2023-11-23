import java.util.*;

class Solution
{
    public int solution(int [][]board)
    {
        int rowCnt = board.length;
        int columnCnt = board[0].length;
        
        for(int i=1;i<rowCnt;i++){
            for(int j=1;j<columnCnt;j++){
                if(board[i][j] == 0){
                    continue;
                }
                
                board[i][j] = Math.min(board[i-1][j-1],Math.min(board[i][j-1],board[i-1][j])) + 1;
            }
        }
        
        
        int answer = 0;
        for(int i=0;i<rowCnt;i++){
            for(int j=0;j<columnCnt;j++){
                answer = Math.max(answer, board[i][j]);
            }
        }

        return answer*answer;
    }
}