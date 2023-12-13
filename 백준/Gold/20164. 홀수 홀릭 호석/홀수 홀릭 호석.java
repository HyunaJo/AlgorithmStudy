import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N;
    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        splitNumber(N, 0);
        bw.write(min+" "+max);
        
        br.close();
        bw.close();
    }

    public static void splitNumber(int num, int oddCnt){
        oddCnt += countOdd(num);

        if(num < 10){ // 1자리 수
            min = Math.min(min, oddCnt);
            max = Math.max(max, oddCnt);
        }
        else if(num < 100){ // 2자리 수
            num = num/10 + num%10;
            splitNumber(num, oddCnt);
        }
        else{ // 3자리 수 이상
            String strNum = Integer.toString(num);
            int len = strNum.length();
            for(int i=0;i<len-2;i++){
                for(int j=i+1;j<len-1;j++){
                    int nextNum = Integer.parseInt(strNum.substring(0,i+1));
                    nextNum += Integer.parseInt(strNum.substring(i+1, j+1));
                    nextNum += Integer.parseInt(strNum.substring(j+1,len));

                    splitNumber(nextNum, oddCnt);
                }
            }
        }


    }

    public static int countOdd(int num){
        int cnt = 0;
        while(num > 0){
            if(num%2 == 1){
                cnt++;
            }
            num /= 10;
        }

        return cnt;
    }
}
