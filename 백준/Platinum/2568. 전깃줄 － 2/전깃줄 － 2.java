import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Line[] lines;
    static int[] dp;
    static int[] locs;
    static StringBuilder sb = new StringBuilder();

    static class Line implements Comparable<Line>{
        int from, to;

        public Line(int from, int to){
            this.from = from;
            this.to = to;
        }

        public int compareTo(Line o){
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
        locs = new int[N];
        int endIdx = 1;
        dp[0] = lines[0].to;
        for(int i=1;i<N;i++){
            if(dp[endIdx-1] < lines[i].to){
                dp[endIdx] = lines[i].to;
                locs[i] = endIdx++;
                continue;
            }

            int idx = findIdx(lines[i].to,endIdx);
            dp[idx] = lines[i].to;
            locs[i] = idx;
        }

        sb.append(N-endIdx).append("\n");
        int printedIdx = endIdx-1;
        for(int i=N-1;i>=0;i--){
            if(locs[i] == printedIdx){
                locs[i] = -1;
                printedIdx--;
                continue;
            }
        }

        for(int i=0;i<N;i++){
            if(locs[i] != -1){
                sb.append(lines[i].from).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static int findIdx(int to, int endIdx){
        int lp = 0;
        int rp = endIdx;
        int mid;

        while(lp<rp){
            mid = (lp+rp)/2;

            if(dp[mid] > to){
                rp = mid;
                continue;
            }

            lp = mid+1;
        }

        return rp;
    }
}
