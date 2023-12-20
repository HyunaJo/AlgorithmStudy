import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N,M;
    public static ArrayList<String> results;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        results = new ArrayList<>();
        for(int i=1;i<=N;i++){
            makeResult(Integer.toString(i),i,1);
        }

        for(String result:results){
            bw.write(result+"\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static void makeResult(String result, int num, int cnt){
        if(cnt == M){
            results.add(result);
            return;
        }

        for(int i=1;i<=N;i++){
            makeResult(result+" "+i,i,cnt+1);
        }
    }
}
