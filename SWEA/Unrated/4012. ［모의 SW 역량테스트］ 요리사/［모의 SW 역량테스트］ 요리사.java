import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int T,N,halfN;
    static int[][] synergy;
    static boolean[] selected;
    static int[] A;
    static int[] B;
    static int ans;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            halfN = N/2;

            synergy = new int[N+1][N+1];
            for(int i=1;i<=N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=1;j<=N;j++){
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            selected = new boolean[N+1];
            A = new int[halfN];
            B = new int[halfN];
            ans = Integer.MAX_VALUE;
            selectIngredients(0,1);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void selectIngredients(int cnt, int idx){
        if(cnt == halfN){
            int aIdx = 0;
            int bIdx= 0;
            for(int i=1;i<=N;i++){
                if(selected[i]){
                    A[aIdx++] = i;
                    continue;
                }

                B[bIdx++] = i;
            }
            int diff = Math.abs(calculateTaste(A) - calculateTaste(B));
            ans = Math.min(ans,diff);
            return;
        }

        if(idx>N){
            return;
        }

        selected[idx] = true;
        selectIngredients(cnt+1,idx+1);
        selected[idx] = false;
        selectIngredients(cnt,idx+1);
    }

    public static int calculateTaste(int[] ingredients){
        int taste = 0;

        for(int i=0;i<halfN;i++){
            for(int j=i+1;j<halfN;j++){
                taste += synergy[ingredients[i]][ingredients[j]];
                taste += synergy[ingredients[j]][ingredients[i]];
            }
        }

        return taste;
    }
}
