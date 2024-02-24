import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int TEAM_CNT = 6;
    static final int TESTCASE_CNT = 4;
    static final int RESULT_CNT = 18;
    static final int GAME_CNT = 15;
    static ArrayList<int[]> games = new ArrayList<>();
    static int[] results;
    static int[] tempResults;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        setGames(0,0,new int[2]); // 경기할 두 팀 리스트

        results = new int[RESULT_CNT];
        for(int t=0;t<TESTCASE_CNT;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<RESULT_CNT;i++){
                results[i] = Integer.parseInt(st.nextToken());
            }

            tempResults = new int[RESULT_CNT];
            if(playGame(0)){
                sb.append("1 ");
                continue;
            }
            sb.append("0 ");
        }
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void setGames(int cnt,int idx, int[] game){
        if(cnt == 2){
            games.add(new int[]{game[0],game[1]});
            return;
        }

        for(int i=idx;i<TEAM_CNT;i++){
            game[cnt] = i;
            setGames(cnt+1,i+1,game);
        }
    }

    public static boolean playGame(int cnt){
        if(cnt == GAME_CNT){
            for(int i=0;i<RESULT_CNT;i++){
                if(results[i]!=tempResults[i]){
                    return false;
                }
            }

            return true;
        }

        int[] game = games.get(cnt);
        int teamA = game[0];
        int teamB = game[1];

        tempResults[teamA*3] += 1;
        tempResults[teamB*3+2] += 1;
        if(playGame(cnt+1)){
            return true;
        }
        tempResults[teamA*3] -= 1;
        tempResults[teamB*3+2] -= 1;

        tempResults[teamA*3+1] += 1;
        tempResults[teamB*3+1] += 1;
        if(playGame(cnt+1)){
            return true;
        }
        tempResults[teamA*3+1] -= 1;
        tempResults[teamB*3+1] -= 1;

        tempResults[teamA*3+2] += 1;
        tempResults[teamB*3] += 1;
        if(playGame(cnt+1)){
            return true;
        }
        tempResults[teamA*3+2] -= 1;
        tempResults[teamB*3] -= 1;

        return false;
    }
}
