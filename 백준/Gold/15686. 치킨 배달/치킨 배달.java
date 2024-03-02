import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int HOUSE = 1;
    static final int CHICKEN = 2;

    static int N,M;
    static int[][] map;
    static ArrayList<Location> houses;
    static ArrayList<Location> chickens;
    static int houseCnt, chickenCnt;
    static int[][] distances;
    static int[] selected;
    static int min;

    static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == HOUSE){
                    houses.add(new Location(i,j));
                    continue;
                }

                if(map[i][j] == CHICKEN){
                    chickens.add(new Location(i,j));
                    continue;
                }
            }
        }

        houseCnt = houses.size();
        chickenCnt = chickens.size();
        distances = new int[houseCnt][chickenCnt];
        for(int i=0;i<houseCnt;i++){
            Location house = houses.get(i);
            for(int j=0;j<chickenCnt;j++){
                Location chicken = chickens.get(j);
                distances[i][j] = Math.abs(house.x-chicken.x)+Math.abs(house.y-chicken.y);
            }
        }

        selected = new int[M];
        min = Integer.MAX_VALUE;
        for(int i=0;i<chickenCnt;i++){
            selected[0] = i;
            findMin(1,i+1);
        }

        bw.write(Integer.toString(min));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMin(int cnt, int idx){
        if(cnt == M){
            min = Math.min(min, getChickenDistance());
            return;
        }

        for(int i=idx;i<chickenCnt;i++){
            selected[cnt] = i;
            findMin(cnt+1, i+1);
        }
    }

    public static int getChickenDistance(){
        int sum = 0;
        for(int i=0;i<houseCnt;i++){
            int dist = distances[i][selected[0]];
            for(int j=1;j<M;j++){
                dist = Math.min(dist,distances[i][selected[j]]);
            }
            sum += dist;
        }
        return sum;
    }
}
