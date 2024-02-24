import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int PLAYER = 9; // 선수 수
    static final int BASE = 3; // 3루까지 존재
    static final int OUT = 0; // 아웃
    static final int HOMERUN = 4; // 홈런

    static int N; // 이닝 수
    static int[][] results; // 이닝 별 선수 결과
    static boolean[] base; // 1,2,3루 선수 여부
    static int[] order; // 선수 순서
    static boolean[] selected; // 순서 정할 때 선택된 선수인지
    static int max = 0; // 최대 점수
    static int orderIdx; // 현재 차례의 선수 번호

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 이닝 수
        results = new int[N+1][PLAYER+1]; // 결과 출력
        for(int t=1;t<=N;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1;i<=PLAYER;i++){
                results[t][i] = Integer.parseInt(st.nextToken());
            }
        }

        // 순서 정해서 게임해보기
        order = new int[PLAYER]; // 타순
        selected = new boolean[PLAYER+1]; // 순서에 선택됐는지 여부
        order[3] = 1; // 4번째 타자는 무조건 1번 선수
        selected[1] = true; // 1번 선수 선택 여부 표시
        makeOrder(0); // 게임 시작

        // 결과 출력
        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();

    }

    public static void makeOrder(int cnt){
        if(cnt == PLAYER){
            orderIdx = 0; // 현재 순서
            int score = 0; // 점수
            for(int inning=1;inning<=N;inning++){
                score += playGame(inning); // 현재 이닝 최종 점수 더해주기
            }

            max = Math.max(max, score); // 최대 점수 변경
        }

        if(cnt == 3){ // 4번 선수는 이미 정해졌으므로
            makeOrder(cnt+1);
            return;
        }

        // 순서 정해주기
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
