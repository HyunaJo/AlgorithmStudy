import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K;
    static int[][] map;
    static Rotate[] rotates;
    static int[] orders;
    static boolean[] selected;
    static int ans;

    static class Rotate{
        int r;
        int c;
        int s;

        public Rotate(int r, int c, int s){
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotates = new Rotate[K];
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotates[i]= new Rotate(r,c,s);
        }

        orders = new int[K];
        selected = new boolean[K];
        ans = Integer.MAX_VALUE;
        findMin(0);

        bw.write(Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }

    public static int caculate(int[][] changedMap){
        int minValue = Integer.MAX_VALUE;

        for(int i=1;i<=N;i++){
            int sum = 0;
            for(int j=1;j<=M;j++){
                sum += changedMap[i][j];
            }
            minValue = Math.min(minValue, sum);
        }
        return minValue;
    }

    public static void findMin(int cnt){
        if(cnt == K){
//            System.out.println(Arrays.toString(orders));
            int[][] changedMap = new int[N+1][M+1];
            for(int i=0;i<=N;i++){
                changedMap[i] = Arrays.copyOf(map[i],M+1);
            }

            for(int order:orders){
                Rotate rotate = rotates[order];
                rotate(changedMap, rotate.r-rotate.s, rotate.c-rotate.s, rotate.r+rotate.s, rotate.c+rotate.s);
            }
            int tmp = caculate(changedMap);
//            System.out.println(tmp);
            ans = Math.min(ans,tmp);
            return;
        }

        for(int i=0;i<K;i++){
            if(selected[i]){
                continue;
            }

            selected[i] = true;
            orders[cnt] = i;
            findMin(cnt+1);
            selected[i] = false;
        }
    }

    public static void rotate(int[][] changedMap, int startX, int startY, int endX, int endY){
        // (r-s, c+s) ~ (r-s, c+s)
        int rowCnt = endX-startX+1;
        int colCnt = endY-startY+1;
        int rotateCnt = (Math.min(rowCnt,colCnt)+1)/2;

        for(int d=0;d<rotateCnt;d++){
            int tmp = changedMap[startX+d][startY+d];

            if(startX+d == endX-d && startY+d == endY-d){
                break;
            }

            for(int i=startX+d;i<endX-d;i++){ // ^
                changedMap[i][startY+d] = changedMap[i+1][startY+d];
            }

            for(int i=startY+d;i<endY-d;i++){ // <
                changedMap[endX-d][i] = changedMap[endX-d][i+1];
            }

            for(int i=endX-d;i>startX+d;i--){ // v
                changedMap[i][endY-d] = changedMap[i-1][endY-d];
            }

            for(int i=endY-d;i>startY+d;i--){ // >
                changedMap[startX+d][i] = changedMap[startX+d][i-1];
            }

            changedMap[startX+d][startY+d+1] = tmp;
        }

    }
}
