import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,d,k,c;
    static int[] dishes;
    static int[] selected;
    static int max;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        dishes = new int[N];
        for(int i=0;i<N;i++){
            dishes[i] = Integer.parseInt(br.readLine());
        }

        selected = new int[d+1];
        selected[c]++;
        int cnt = 1;
        int lp = 0;
        int rp = k-1;
        for(int i=lp;i<=rp;i++){
            if(selected[dishes[i]] == 0){
                cnt++;
            }
            selected[dishes[i]]++;
        }

        max = cnt;
        while(lp<N){
            if(--selected[dishes[lp]] == 0){
                cnt--;
            }
            lp++;
            rp = (rp+1)%N;
            if(selected[dishes[rp]]++ == 0){
                cnt++;
            }
            max = Math.max(max,cnt);
        }

        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();
    }
}
