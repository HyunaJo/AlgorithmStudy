import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static int N, L;
    public static Deque<Num> dq = new ArrayDeque<>();

    public static class Num{
        int idx;
        int value;

        public Num(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            int inputNum = Integer.parseInt(st.nextToken());

            if(i-L>0 && dq.peekFirst().idx<i-L+1){ // i-L+1>1 => A의 첫범위가 A2~, A3~ ...인 경우
                // 가장 오래된 값 빼기
                dq.pollFirst();
            }

            while(!dq.isEmpty()){
                if(dq.peekLast().value>inputNum)
                    dq.pollLast();
                else
                    break;
            }

            dq.addLast(new Num(i, inputNum));
            bw.write(Integer.toString(dq.peekFirst().value)+" ");
        }

        bw.flush();
    }
}
