import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N, L;
    public static int startNum = -1;
    public static int minLen = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 목표 합
        L = Integer.parseInt(st.nextToken()); // 정수 리스트 최소 길이

        for(int i=0;i<N;i++){
//            long sum = (long)(i+L-1)*(i+L)/2 - (long)i*(i+1)/2 + i; // i~i+L-1까지의 합
            int sum = 0;
            for(int j=i; j<i+L; j++)
                sum+=j;



            if(sum == N){
                startNum = i;
                minLen = L;
                break;
            }
            else if(sum > N){ // 불가능
                break;
            }
            else{ // sum < N
                int len = L; // 수열 길이

                for(int j=i+L;j<N;j++){
                    if(++len > 100){
                        break;
                    }

                    sum += j;
                    if(sum > N){
                        break;
                    }

                    if(sum == N){
                        if(minLen > len){ // 수열 길이가 이전보다 짧은 경우
                            minLen = len;
                            startNum = i;
                        }
                    }
                }
            }
        }

        if(startNum == -1){
            bw.write("-1");
        }
        else{
            for(int i=startNum;i<startNum+minLen;i++){
                bw.write(Integer.toString(i)+" ");
            }
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
