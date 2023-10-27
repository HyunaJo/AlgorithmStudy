import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N,M;
    public static ArrayList<Integer>[] subjects;
    public static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 과목의 수
        M = Integer.parseInt(st.nextToken()); // 선수 조건의 수

        subjects = new ArrayList[N+1];
        dp = new int[N+1];
        for(int i=1;i<=N;i++){
            subjects[i] = new ArrayList<Integer>();
            dp[i] = 1;
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int prerequisite = Integer.parseInt(st.nextToken());
            int subject = Integer.parseInt(st.nextToken());

            subjects[prerequisite].add(subject);
        }


        for(int i=1;i<=N;i++){
            ArrayList<Integer> nextSubjects = subjects[i];
            int nextSubjectCnt = nextSubjects.size();

            for(int j=0;j<nextSubjectCnt;j++){
                int nextSubject = nextSubjects.get(j);
                dp[nextSubject] = Math.max(dp[nextSubject], dp[i]+1);
            }
        }

        for(int i=1;i<=N;i++){
            bw.write(Integer.toString(dp[i])+" ");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
