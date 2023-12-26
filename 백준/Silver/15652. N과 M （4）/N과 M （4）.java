import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N,M;
    public static ArrayList<String> answers;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answers = new ArrayList<>();
        for(int i=1;i<=N;i++){
            makeArray(Integer.toString(i),1, i);
        }

        for(String answer:answers){
            bw.write(answer+"\n");
        }
        bw.flush();
    }

    public static void makeArray(String str, int cnt, int num){
        if(cnt == M){
            answers.add(str);
            return;
        }

        for(int i=num;i<=N;i++){
            makeArray(str+" "+i, cnt+1, i);
        }
    }
}
