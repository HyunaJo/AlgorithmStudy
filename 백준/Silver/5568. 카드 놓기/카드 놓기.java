import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {
    public static int n,k;
    public static HashSet<Long> completedNums = new HashSet<>();
    public static String[] nums;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine()); // 카드 n장
        k = Integer.parseInt(br.readLine()); // k장 선택

        nums = new String[n];
        visited = new boolean[n];
        for(int i=0;i<n;i++)
            nums[i] = br.readLine();

        makeNum(k, "");

        bw.write(Integer.toString(completedNums.size()));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void makeNum(int rest, String result){
        if(rest == 0){
            completedNums.add(Long.parseLong(result));
            return;
        }

        for(int i=0;i<n;i++){
            if(!visited[i]) {
                visited[i] = true;
                makeNum(rest-1, result+nums[i]);
                visited[i] = false;
            }
        }
    }
}
