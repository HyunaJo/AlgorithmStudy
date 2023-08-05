import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N,M;
    public static int[][] map;
    public static ArrayList<Location> chickens = new ArrayList<>();
    public static ArrayList<Location> houses = new ArrayList<>();
    public static int ans = Integer.MAX_VALUE;

    public static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                int type = Integer.parseInt(st.nextToken());
                map[i][j] = type;
                if(type == 1) // 집인 경우
                    houses.add(new Location(i,j));
                else if(type == 2) // 치킨집인 경우
                    chickens.add(new Location(i,j));
            }
        }

        dfs(chickens.size()-M, 0);

        bw.write(Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void dfs(int rest, int idx){
        if(rest == 0){ // M개 남기고 폐업한 경우
            int dist = getDist();
            if(ans > dist)
                ans = dist;
            return;
        }

        if(idx > chickens.size()-1) // 더이상 폐업할 치킨집 없는 경우
            return;

        // idx에 해당하는 치킨집 폐업하기
        int closedChickenY = chickens.get(idx).y; // 폐업할 치킨집
        chickens.get(idx).y = -1;
        dfs(rest-1, idx+1);
        chickens.get(idx).y = closedChickenY;

        // 폐업 안하기
        dfs(rest, idx+1);
    }

    public static int getDist(){
        int houseCnt = houses.size();
        int chickenCnt = chickens.size();

        int distSum = 0;
        for(int i=0;i<houseCnt;i++){
            Location house = houses.get(i);

            int minDist = Integer.MAX_VALUE;
            for(int j=0;j<chickenCnt;j++){
                Location chicken = chickens.get(j);

                if(chicken.y == -1) // 폐업한 경우
                    continue;

                int dist = Math.abs(house.x-chicken.x)+Math.abs(house.y-chicken.y);
                if(dist<minDist)
                    minDist = dist;
            }
            distSum += minDist;
        }
        return distSum;
    }
}
