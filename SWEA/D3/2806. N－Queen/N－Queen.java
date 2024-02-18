import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static int T,N;
    static int[] board; // 체스판
    static int ans; // 가능한 경우의 수
    static StringBuilder sb = new StringBuilder(); // 결과

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine()); // 체스판 크기
            board = new int[N]; // 퀸을 놓는 열 => board[i]=1 -> i행 1열에 퀸 놓음
            ans = 0; // 가능한 경우의 수

            // 가능한 경우의 수 찾기
            countPossible(0);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        // 결과 출력
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void countPossible(int row){
        if(row == N){ // N개 행에 대해서 퀸을 다 놓은 경우
            ans++; // 가능한 경우의 수 증가
            return;
        }

        // 0~N-1번째 열에 대해 퀸 놓아보기
        for(int i=0;i<N;i++){
            if(isPossible(row, i)){ // 가능한 경우
                board[row] = i; // row번째 행, i번째 열에 퀸 놓기
                countPossible(row+1); // 다음행에 퀸 놓아보기
            }
        }
    }

    public static boolean isPossible(int row, int col){
        for(int i=0;i<row;i++){
            // 같은 열에 놓인 퀸 있는지 확인
            if(board[i] == col){
                return false;
            }

            // 대각선 가능한지 확인
            if(row-i==col-board[i] || row-i==board[i]-col){
                return false;
            }
        }

        return true;
    }
}
