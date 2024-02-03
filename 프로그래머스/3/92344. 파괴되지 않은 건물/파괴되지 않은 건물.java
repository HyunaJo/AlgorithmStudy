class Solution {
    static int[][] sum;
    static int N,M;
    
    public int solution(int[][] board, int[][] skill) {
        N = board.length; // 행 개수
        M = board[0].length; // 열 개수
        sum = new int[N+1][M+1]; // 공격+회복 담기
        
        int skillCnt = skill.length; // 공격+회복 횟수
        for(int i=0;i<skillCnt;i++){
            int degree = skill[i][5];
            if(skill[i][0] == 1){
                degree *= -1;
            }
            
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            
            sum[r1][c1] += degree;
            sum[r1][c2+1] -= degree;
            sum[r2+1][c1] -= degree;
            sum[r2+1][c2+1] += degree;
        }
        
        // -> 방향 합
        for(int i=0;i<=N;i++){
            for(int j=1;j<=M;j++){
                sum[i][j] += sum[i][j-1];
            }
        }
        
        // 아래쪽 방향 합
        for(int j=0;j<=M;j++){
            for(int i=1;i<=N;i++){
                sum[i][j] += sum[i-1][j];
            }
        }
        
        int answer = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(board[i][j]+sum[i][j]>0){
                    answer++;
                }
            }
        }
        return answer;
    }
    
}