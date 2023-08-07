import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static Deque<Integer> stack = new ArrayDeque<>();
    public static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        while(N-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (Integer.parseInt(st.nextToken())){
                case 1: // 정수 스택에 넣기
                    stack.add(Integer.parseInt(st.nextToken()));
                    break;
                case 2: // 맨 위의 정수 빼고 출력
                    if(stack.isEmpty())
                        bw.write("-1\n");
                    else
                       bw.write(Integer.toString(stack.pollLast())+"\n");
                    break;
                case 3: // 정수 개수 출력
                    bw.write(Integer.toString(stack.size())+"\n");
                    break;
                case 4:
                    if(stack.isEmpty())
                        bw.write("1\n");
                    else
                        bw.write("0\n");
                    break;
                case 5:
                    if(stack.isEmpty())
                        bw.write("-1\n");
                    else
                        bw.write(Integer.toString(stack.peekLast())+"\n");
                    break;
            }
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
