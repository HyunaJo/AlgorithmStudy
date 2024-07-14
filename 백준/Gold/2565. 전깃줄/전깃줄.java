import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Line[] lines;
    static int[] dp;
    static int maxRemained;

    static class Line implements Comparable<Line> {
        int from, to;

        public Line(int from, int to){
            this.from = from;
            this.to = to;
        }

        public int compareTo(Line o) {
            return this.from - o.from;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        lines = new Line[N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lines);
        dp = new int[N];
        maxRemained = 0;
        for(int i=0;i<N;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(lines[i].to < lines[j].to){
                    continue;
                }

                dp[i] = Math.max(dp[j]+1, dp[i]);
            }
            maxRemained = Math.max(dp[i],maxRemained);
        }

        bw.write(Integer.toString(N-maxRemained));
        bw.flush();
    }
}