import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int n,m;
    public static int[][] size; // 정사각형의 한 변 길이
    public static int[][] map; // 사용자 입력
    public static int ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        size = new int[n+1][m+1];
        map = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            String input = br.readLine();
            for(int j=0;j<m;j++)
                map[i][j+1] = input.charAt(j)-'0';
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(map[i][j] == 1){ // map에서 1인 경우
                    // 위, 왼쪽 대각선 위, 왼쪽 살펴보기
                    int min = Math.min(size[i-1][j],size[i-1][j-1]);
                    min = Math.min(min,size[i][j-1]);

                    size[i][j] = min+1;
                    ans = Math.max(ans, min+1);
                }
            }
        }
        System.out.println(ans*ans);
    }
}
