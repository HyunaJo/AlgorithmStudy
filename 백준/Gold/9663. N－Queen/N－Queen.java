import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N;
    public static int[] arr;
    public static int ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        nQueen(0);
        bw.write(Integer.toString(ans));
        br.close();
        bw.close();
    }

    public static void nQueen(int row){
        if(row == N){
            ans++;
            return;
        }

        for(int j=0;j<N;j++){ // j열에 체스 놓는 경우
            boolean isPossible = true; // 가능한 위치인지 판단
            for(int i=0;i<row;i++){ // i행
                if(arr[i]==j || row-i==j-arr[i] || row-i==arr[i]-j) { // 같은 열에 존재, 대각선 확인
                    isPossible = false;
                    break;
                }
            }

            if(isPossible){
                arr[row] = j;
                nQueen(row+1);
            }

        }
    }
}
