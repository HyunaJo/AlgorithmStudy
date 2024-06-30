import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int T;
    static Deque<Integer> nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        while(T-->0){
            String commands = br.readLine();
            int n = Integer.parseInt(br.readLine());

            String input = br.readLine().replace("[","").replace("]","");
            String[] inputs = input.split(",");
            nums = new ArrayDeque<>();
            for(int i=0;i<n;i++){
                nums.add(Integer.parseInt(inputs[i]));
            }

            int cmdCnt = commands.length();
            boolean isError = false;
            boolean isReverse = false;
            for(int i=0;i<cmdCnt;i++){
                Character cmd = commands.charAt(i);

                if(cmd == 'R'){
                    isReverse = !isReverse;
                    continue;
                }

                if(nums.isEmpty()){
                    isError = true;
                    sb.append("error\n");
                    break;
                }

                if(isReverse){
                    nums.pollLast();
                }
                else{
                    nums.pollFirst();
                }
            }

            if(!isError){
                sb.append("[");
                while(nums.size()>1){
                    sb.append((isReverse)?nums.pollLast():nums.pollFirst()).append(",");
                }
                sb.append((nums.isEmpty())?"":nums.poll()).append("]\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
}

