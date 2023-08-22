import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static int N; // 재현시 크기
    public static int[][] map; // 재현시 인구
    public static boolean[][] border;
    public static ArrayList<Integer> populations = new ArrayList<>();
    public static int totalPopulation = 0;
    public static final int INF = 1_000_000_000;
    public static int answer = INF;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int population = Integer.parseInt(st.nextToken());
                map[i][j] = population;
                totalPopulation += population;
            }
        }

        for(int x=1;x<=N;x++){
            for(int y=1;y<=N;y++){
                for(int d1=1;d1<=N;d1++){
                    for(int d2=1;d2<=N;d2++){
                        if(x+d1+d2>N || y-d1<1 || y+d2>N){
                            continue;
                        }
                        makeBorder(x,y,d1,d2);
                        countPopulation(x,y,d1,d2);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public static void makeBorder(int x, int y, int d1, int d2){
        border = new boolean[N+1][N+1];

        for(int i=0;i<=d1;i++){
            border[x+i][y-i] = true;
            border[x+d2+i][y+d2-i] = true;
        }

        for(int i=0;i<=d2;i++){
            border[x+i][y+i] = true;
            border[x+d1+i][y-d1+i] = true;
        }
    }

    public static void countPopulation(int x, int y, int d1, int d2){
        populations.clear();
        int tmpTotalPopulation = totalPopulation;
        int population = 0;

        // 1구역
        for(int i=1;i<x+d1;i++){
            for(int j=1;j<=y;j++){
                if(border[i][j])
                    break;
                population += map[i][j];
            }
        }
        tmpTotalPopulation -= population;
        populations.add(population);

        // 2구역
        population = 0;
        for(int i=1;i<=x+d2;i++){
            for(int j=N;j>=y+1;j--){
                if(border[i][j])
                    break;
                population += map[i][j];
            }
        }
        tmpTotalPopulation -= population;
        populations.add(population);

        // 3구역
        population = 0;
        for(int i=x+d1;i<=N;i++){
            for(int j=1;j<y-d1+d2;j++){
                if(border[i][j])
                    break;
                population += map[i][j];
            }
        }
        tmpTotalPopulation -= population;
        populations.add(population);

        // 4구역
        population = 0;
        for(int i=x+d2+1;i<=N;i++){
            for(int j=N;j>=y-d1+d2;j--){
                if(border[i][j])
                    break;
                population += map[i][j];
            }
        }
        tmpTotalPopulation -= population;
        populations.add(population);

        // 5구역
        populations.add(tmpTotalPopulation);

        Collections.sort(populations);
        answer = Math.min(answer, populations.get(4)-populations.get(0));
    }
}
