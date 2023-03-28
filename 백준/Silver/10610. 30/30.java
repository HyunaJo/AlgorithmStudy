import java.io.*;
import java.util.Arrays;
import java.util.Collections;

// 그리디 알고리즘 - q10610 (30)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String N = br.readLine();
        char[] chars = N.toCharArray();
        Integer[] nums = new Integer[chars.length];
        int numLength = chars.length;

        for(int i=0;i<numLength;i++)
            nums[i] = Integer.parseInt(String.valueOf(chars[i]));

        Arrays.sort(nums);
        if(nums[0]!=0){ // 0이 없는 경우 => 30의 배수될 수 없음
            bw.write("-1");
        }
        else{
            int total = 0;
            for(int i=1;i<numLength;i++)
                total += nums[i];

            if(total%3!=0) // 자리수 총합이 3의 배수가 아닌 경우 => 30의 배수 될 수 없음
                bw.write("-1");
            else // 30의 배수인 경우
                for(int i=numLength-1;i>=0;i--)
                    bw.write(Integer.toString(nums[i]));
        }

        bw.flush();
    }
}
