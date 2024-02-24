import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int PLAYER = 9;
    static final int BASE = 3;
    static final int OUT = 0;
    static final int HIT = 4;
    static final int DOUBLE = 2;
    static final int TRIPLE = 3;
    static final int HOMERUN = 4;

    static int N;
    static int[][] results;
    static boolean[] base;
    static int[] order;
    static boolean[] selected;
    static int max = 0;
    static int orderIdx;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        results = new int[N+1][PLAYER+1];
        for(int t=1;t<=N;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1;i<=PLAYER;i++){
                results[t][i] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[PLAYER];
        selected = new boolean[PLAYER+1];
        order[3] = 1;
        selected[1] = true;
        makeOrder(0);
        System.out.println(max);

    }

    public static void makeOrder(int cnt){
        if(cnt == PLAYER){
            orderIdx = 0;
            if(order[0] == 5 && order[1]==6 && order[2]==7){
                System.out.print("");
            }
            int score = 0;
            for(int inning=1;inning<=N;inning++){
                score += playGame(inning);
            }

            max = Math.max(max, score);
//            System.out.println(max);
        }

        if(cnt == 3){
            makeOrder(cnt+1);
            return;
        }

        for(int i=2;i<=PLAYER;i++){
            if(selected[i]){
                continue;
            }

            selected[i] = true;
            order[cnt] = i;
            makeOrder(cnt+1);
            selected[i] = false;
        }
    }

    public static int playGame(int inning){
        base = new boolean[BASE+1];
        int score = 0;
        int outCnt = 0;

        while(outCnt < 3){
            int player = order[orderIdx];
            orderIdx = (orderIdx+1)%9;

            if(results[inning][player] == OUT){
                outCnt++;
                continue;
            }

            // 홈런
            if(results[inning][player] == HOMERUN){
                for(int i=1;i<=BASE;i++){
                    if(base[i]){
                        base[i] = false;
                        score++;
                    }
                }
                score++;
                continue;
            }

            score += run(results[inning][player]);
        }

        return score;
    }

    public static int run(int result){
        int plusScore = 0;
        for(int i=BASE;i>=1;i--){
            if(!base[i]){
                continue;
            }

            if(i+result>BASE){
                plusScore++;
                base[i] = false;
                continue;
            }

            base[i+result] = true;
            base[i] = false;
        }
        base[result] = true;

        return plusScore;
    }
}
