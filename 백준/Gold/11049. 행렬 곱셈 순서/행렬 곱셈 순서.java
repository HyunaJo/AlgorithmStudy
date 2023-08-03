import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N; // 행렬의 개수
    public static int[][] cnt; // 곱산 연산 횟수. cnt[i][j] = i번째~j번째 행렬까지의 곱산 연산 횟수
    public static Matrix[] matrix; // 행렬 정보
    public static int ans = Integer.MAX_VALUE;

    public static class Matrix{
        int r; // 행 크기
        int c; // 열 크기

        public Matrix(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        matrix = new Matrix[N+1];
        cnt = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i] = new Matrix(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        bw.write(Integer.toString(findMin(1,N)));
        bw.flush();

        br.close();
        bw.close();
    }

    public static int findMin(int startId, int endId){
        if(startId+1 == endId){
            Matrix start = matrix[startId];
            Matrix end = matrix[endId];
            return start.r*start.c*end.c;
        }
        else if(startId==endId) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for(int i=endId-1;i>=startId;i--){
            if(cnt[startId][i] == 0 && startId!=i)
                cnt[startId][i] = findMin(startId,i);

            if(cnt[i+1][endId] == 0 && i+1!=endId)
                cnt[i+1][endId] = findMin(i+1,endId);

            min = Math.min(min, cnt[startId][i]+cnt[i+1][endId]+matrix[startId].r*matrix[i].c*matrix[endId].c);
        }
        return min;
    }
}
