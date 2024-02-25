import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int ARCHER_CNT = 3;
    static int N,M,D;
    static int[][] map; // 격자판
    static int[] archers;
    static boolean[] selected;
    static ArrayList<Location> enemies = new ArrayList<>();
    static int enemyCnt = 0;
    static int enemyMinX = 15;
    static boolean[] attacked;
    static int max = 0;

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
        N = Integer.parseInt(st.nextToken()); // 행의 수
        M = Integer.parseInt(st.nextToken()); // 열의 수
        D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한

        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1){
                    enemies.add(new Location(i,j));
                    enemyCnt++;
                    enemyMinX = Math.min(enemyMinX,i);
                }
            }
        }

        archers = new int[ARCHER_CNT];
        selected = new boolean[M];
        setArcher(0,0);

        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void setArcher(int cnt, int idx){
        if(cnt == ARCHER_CNT){
            max = Math.max(max,defendCastle(N));
            return;
        }

        if(idx==M){
            return;
        }

        for(int i=idx;i<M;i++){
            if(selected[i]){
                continue;
            }

            selected[i] = true;
            archers[cnt] = i;
            setArcher(cnt+1,i+1);
            selected[i] = false;
        }
    }

    public static int defendCastle(int archarX){
        int attackedCnt = 0;
        attacked = new boolean[enemyCnt];
        HashSet<Integer> attackSet = new HashSet<>();

        while(archarX>enemyMinX && attackedCnt<enemyCnt){
            for(int archerY:archers){
                int attackIdx = -1;
                int minDistance = Integer.MAX_VALUE;
                int minEnemyY = M;

                for(int i=0;i<enemyCnt;i++){
                    if(attacked[i]){
                        continue;
                    }

                    Location enemy = enemies.get(i);
                    if(enemy.x >= archarX){
                        continue;
                    }

                    int distance = getDistance(enemy, archarX, archerY);
                    if(distance > D){
                        continue;
                    }

                    if(minDistance < distance){
                        continue;
                    }

                    if(minDistance == distance){
                        if(minEnemyY > enemy.y){
                            minEnemyY = enemy.y;
                            attackIdx = i;
                        }
                        continue;
                    }

                    minDistance = distance;
                    minEnemyY = enemy.y;
                    attackIdx = i;
                }

                if(attackIdx != -1){
                    attackSet.add(attackIdx);
                }
            }

            for(int idx:attackSet){
                attacked[idx] = true;
                attackedCnt++;
            }
            attackSet.clear();

            archarX--;
        }

        return attackedCnt;
    }

    public static int getDistance(Location enemy, int archerX, int archerY){
        return Math.abs(enemy.x-archerX)+Math.abs(enemy.y-archerY);
    }
}
