import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int[][] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        nums = new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<=i;j++){
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=n-2;i>=0;i--){
            for(int j=i;j>=0;j--){
                nums[i][j] = Math.max(nums[i+1][j], nums[i+1][j+1])+nums[i][j];
            }
        }

        bw.write(Integer.toString(nums[0][0]));
        bw.flush();
        
        br.close();
        bw.close();
    }
}
