import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] abilities;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        abilities = new int[N];
        for(int i=0;i<N;i++){
            abilities[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(abilities);
        bw.write(Long.toString(findCnt()));
        bw.flush();
    }

    public static long findCnt(){
        long cnt = 0;

        for(int i=0;i<N-2;i++){
            if(abilities[i] > 0){
                break;
            }

            int lp = i+1;
            int rp = N-1;
            while(lp < rp){
                int sum = abilities[i] + abilities[lp] + abilities[rp];
                if(sum > 0){
                    rp--;
                    continue;
                }

                if(sum < 0){
                    lp++;
                    continue;
                }

                // sum == 0
                if(abilities[lp] == abilities[rp]){
                    cnt += combination(rp-lp+1);
                    break;
                }
                int lpCnt = 1;
                int rpCnt = 1;
                while(abilities[lp] == abilities[lp+1]){
                    lpCnt++;
                    lp++;
                }

                while(abilities[rp] == abilities[rp-1]){
                    rpCnt++;
                    rp--;
                }

                cnt += (long) lpCnt * rpCnt;
                lp++;
            }
        }
        return cnt;
    }

    public static long combination(int n){
        return (long) n *(n-1)/2;
    }
}
