import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N,M;
    static Set<Integer> inputs;
    static ArrayList<Integer> nums;
    static ArrayList<String> answers;
    static int numCnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputs = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            inputs.add(Integer.parseInt(st.nextToken()));
        }

        nums = new ArrayList<>(inputs);
        numCnt = nums.size();
        answers = new ArrayList<>();
        for(int i=0;i<numCnt;i++){
            makeAnswer(1,i, Integer.toString(nums.get(i)));
        }

        for(String answer:answers){
            bw.write(answer+"\n");
        }
        bw.flush();
    }

    public static void makeAnswer(int cnt, int idx, String str){
        if(cnt == M){
            answers.add(str);
            return;
        }

        for(int i=idx;i<numCnt;i++){
            makeAnswer(cnt+1,i,str+" "+nums.get(i));
        }
    }
}
