import java.io.*;
import java.util.*;

public class Main {
    public static int N,M;
    public static int[] nums;
    public static boolean[] visited;
    public static HashSet<String> sequences = new HashSet<>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception{

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        findSequence(M, 0, "");
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findSequence(int rest, int priorNum,String sequence) throws Exception{
        if(rest==0){
            if(!sequences.contains(sequence)) {
                sequences.add(sequence);
                bw.write(sequence+"\n");
            }
            return;
        }

        for(int i=0;i<N;i++){
            if(!visited[i] && priorNum<=nums[i]){
                visited[i] = true;
                if(sequence == "")
                    findSequence(rest-1, nums[i], Integer.toString(nums[i]));
                else
                    findSequence(rest-1, nums[i], sequence+ " "+Integer.toString(nums[i]));
                visited[i] = false;
            }
        }
    }
}
