import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] ability;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        ability = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            ability[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N-1;
        int maxAbility = 0;
        while(start<end){
            maxAbility = Math.max(maxAbility, (end-start-1)*Math.min(ability[start],ability[end]));
            if(ability[start] < ability[end]){
                start++;
            }
            else{
                end--;
            }
        }
        bw.write(Integer.toString(maxAbility));
        bw.flush();

        br.close();
        bw.close();
    }
}
