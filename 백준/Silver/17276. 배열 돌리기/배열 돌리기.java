import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        while(T>0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 배열의 크기
            int d = Integer.parseInt(st.nextToken()); // 각도

            if(d<0){ // d 음수일 경우 양수로 바꾸기
                d += 360;
            }

            // 초기 배열 입력 받기
            int[][] originalArr = new int[n+1][n+1];
            int[][] modifiedArr = new int[n+1][n+1];
            for(int i=1;i<=n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<=n;j++){
                    int num = Integer.parseInt(st.nextToken());
                    originalArr[i][j] = num;
                    modifiedArr[i][j] = num;
                }
            }

            for(int k=0;k<d/45;k++) {
                for (int i = 1; i <= n; i++) {
                    modifiedArr[i][(n + 1) / 2] = originalArr[i][i];
                    modifiedArr[i][n - i + 1] = originalArr[i][(n + 1) / 2];
                    modifiedArr[(n + 1) / 2][i] = originalArr[n - i + 1][i];
                    modifiedArr[i][i] = originalArr[(n + 1) / 2][i];
                }
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        originalArr[i][j] = modifiedArr[i][j];
                    }
                }
            }
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    bw.write(Integer.toString(modifiedArr[i][j])+" ");
                }
                bw.write("\n");
            }
            T--;
        }
        bw.flush();
    }
}
