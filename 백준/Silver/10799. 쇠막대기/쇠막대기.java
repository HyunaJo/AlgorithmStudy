import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static String input;
    public static int answer = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine();
        cutBar();
        bw.write(Integer.toString(answer));
        bw.flush();
        
        bw.close();
        br.close();
    }

    public static void cutBar(){
        Deque<Character> dq = new ArrayDeque<>();
        int currentCnt = 0;
        char priorChar = '(';

        int inputLen = input.length();
        for(int i=0;i<inputLen;i++){
            char ch = input.charAt(i);

            if(ch == '('){
                dq.addLast('(');
                currentCnt++;
                priorChar = '(';
            }
            else if(priorChar == ')'){ // 쇠막대기 오른쪽 끝점
                currentCnt--;
                dq.pollLast();
                answer += 1;
                priorChar = ')';
            }
            else{ // 레이저인 경우
                currentCnt--;
                dq.pollLast();
                answer += currentCnt;
                priorChar = ')';
            }
        }
    }
}
