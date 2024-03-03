import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int PLAYER = 9;
    static final int OUT = 0;

    static int N;
    static int[][] results;
    static int[] order;
    static boolean[] selected;
    static int orderIdx;
    static int max = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 이닝 수
        results = new int[N][PLAYER+1];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=PLAYER;j++){
                results[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[PLAYER];
        selected = new boolean[PLAYER+1];
        order[3] = 1;
        selected[1] = true;
        makeOrder(0);

        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void makeOrder(int cnt){
        if(cnt == PLAYER){
            orderIdx = 0;
            int score = 0;
            for(int i=0;i<N;i++){
                score += playGame(i);
            }
            max = Math.max(max, score);
            return;
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
        int outCnt = 0;
        int score = 0;
        boolean[] base = new boolean[4];

        while(outCnt<3){
            int playerIdx = order[orderIdx];
            orderIdx = (orderIdx+1)%9;

            if(results[inning][playerIdx] == OUT){
                outCnt++;
                continue;
            }

            score += run(base, results[inning][playerIdx]);
        }

        return score;
    }

    public static int run(boolean[] base, int movingCnt){
        int score = 0;

        for(int i=3;i>0;i--){
            if(!base[i]){
                continue;
            }

            base[i] = false;
            if(i+movingCnt > 3){
                score++;
                continue;
            }
            base[i+movingCnt] = true;
        }

        if(movingCnt != 4){
            base[movingCnt] = true;
        }

        return (movingCnt==4)?score+1:score;
    }
}
