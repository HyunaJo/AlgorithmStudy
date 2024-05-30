import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] length;
    static int minDiff;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        length = new int[N];
        for(int i=0;i<N;i++){
            length[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(length);
        minDiff = Integer.MAX_VALUE;
        for(int i=0;i<N-3;i++){
            for(int j=N-1;j>i+2;j--){
                int elsa = length[i] + length[j];

                findMin(i+1,j-1, elsa);
            }
        }

        bw.write(Integer.toString(minDiff));
        bw.flush();
    }

    public static void findMin(int lp, int rp, int elsa){
        while(lp < rp){
            int anna = length[lp] + length[rp];

            if(anna == elsa){
                minDiff = 0;
                return;
            }

            minDiff = Math.min(Math.abs(elsa - anna), minDiff);
            if(elsa > anna){
                lp++;
                continue;
            }

            rp--;
        }
    }
}
