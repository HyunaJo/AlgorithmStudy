import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N; // 남은 일수
    public static Consulting[] consultings;
    public static int[] profit;

    public static class Consulting{
        int date;
        int cost;

        public Consulting(int date, int cost){
            this.date = date;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        consultings = new Consulting[N];
        profit = new int[N+1];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            consultings[i] = new Consulting(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        int maxProfit = 0; // i일까지의 최대 수익
        int nextDate = 0;
        for(int i=0;i<N;i++){
            maxProfit = Math.max(maxProfit, profit[i]);

            nextDate = i + consultings[i].date;
            if(nextDate<=N){ // 퇴사 전에 끝나는 상담일 경우
                profit[nextDate] = Math.max(profit[nextDate], maxProfit+consultings[i].cost);
            }
        }

        for(int i=0;i<=N;i++){
            maxProfit = Math.max(maxProfit, profit[i]);
        }
        
        bw.write(Integer.toString(maxProfit));
        bw.flush();

        br.close();
        bw.close();
    }
}
