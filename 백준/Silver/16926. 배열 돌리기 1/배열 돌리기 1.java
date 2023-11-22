import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N,M,rotCnt;
    public static int[][] nums;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rotCnt = Integer.parseInt(st.nextToken());

        nums = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(rotCnt-->0){
            rotateMatrix();
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                bw.write(nums[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static void rotateMatrix(){
        int rotateLineCnt = Math.min(N,M)/2;
        for(int i=0;i<rotateLineCnt;i++){
            int tmp = nums[i][i];

            for(int j=i;j<M-i-1;j++){ // 왼쪽으로 이동
                nums[i][j] = nums[i][j+1];
            }

            for(int j=i;j<N-i-1;j++){ // 위로 이동
                nums[j][M-i-1] = nums[j+1][M-i-1];
            }

            for(int j=M-i-1;j>i;j--){ // 오른쪽으로 이동
                nums[N-i-1][j] = nums[N-i-1][j-1];
            }

            for(int j=N-i-1;j>i;j--){ // 아래쪽으로 이동
                nums[j][i] = nums[j-1][i];
            }

            nums[i+1][i] = tmp;
        }
    }
}