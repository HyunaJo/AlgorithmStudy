import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] shape1 = {{0,0},{0,1},{0,2},{0,3}};
    static int[][] shape2 = {{0,0},{0,1},{1,0},{1,1}};
    static int[][][] shape3 = {{{0,0},{1,0},{2,0},{2,1}},
                                {{0,0},{0,1},{0,2},{-1,2}},
                                {{0,0},{-1,0},{-2,0},{-2,-1}},
                                {{0,0},{0,-1},{0,-2},{1,-2}}};
    static int[][][] shape4 = {{{0,0},{1,0},{1,1},{2,1}},
                                {{0,0},{0,1},{-1,1},{-1,2}},
                                {{0,0},{-1,0},{-1,-1},{-2,-1}},
                                {{0,0},{0,-1},{1,-1},{1,-2}}};
    static int[][][] shape5 = {{{0,0},{0,-1},{0,1},{1,0}},
                                {{0,0},{0,-1},{0,1},{-1,0}}};
    static int ans;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        for(int i=0;i<N;i++){
           for(int j=0;j<M;j++){
               calculateShape1(i,j);
               calculateShape2(i,j);
               calculateShape3(i,j);
               calculateShape4(i,j);
               calculateShape5(i,j);
           }
        }
        bw.write(Integer.toString(ans));
        bw.flush();
    }

    public static void calculateSum(int[][] shape, int xIdx, int yIdx, int x, int y){
        int sum = 0;
        for(int i=0;i<4;i++){
            int dx = x + shape[i][xIdx];
            int dy = y + shape[i][yIdx];

            if(isOutOfMap(dx,dy)){
                return;
            }
            sum += map[dx][dy];
        }

        ans = Math.max(ans, sum);
    }

    public static void calculateShape1(int x, int y){
        // 가로 4칸
        calculateSum(shape1, 0, 1, x, y);
        // 세로 4칸
        calculateSum(shape1, 1, 0, x, y);
    }

    public static void calculateShape2(int x, int y){
        calculateSum(shape2, 0, 1, x , y);
    }

    public static void calculateShape3(int x, int y){
        for(int i=0;i<4;i++){
            calculateSum(shape3[i], 0, 1, x, y);
            calculateSum(shape3[i], 1, 0, x, y);
        }
    }

    public static void calculateShape4(int x, int y){
        for(int i=0;i<4;i++){
            calculateSum(shape4[i], 0, 1, x, y);
            calculateSum(shape4[i], 1, 0, x, y);
        }
    }

    public static void calculateShape5(int x, int y){
        for(int i=0;i<2;i++){
            calculateSum(shape5[i], 0, 1, x, y);
            calculateSum(shape5[i], 1, 0, x, y);
        }
    }

    public static boolean isOutOfMap(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
