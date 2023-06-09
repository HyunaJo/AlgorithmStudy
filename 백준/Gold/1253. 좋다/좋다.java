import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 수의 개수
        long[] nums = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            nums[i] = Long.parseLong(st.nextToken());
        Arrays.sort(nums); // 오름차순 정렬

        int goodCnt = 0;
        for(int k=0;k<N;k++){
            long target = nums[k];
            int i = 0;
            int j = N-1;

            while(i<j){
                long sum = nums[i]+nums[j];
                if(sum>target)
                    j--;
                else if(sum<target)
                    i++;
                else{ // 합이 target과 같은 경우
                    if(i!=k && j!=k){ // nums[i]나 nums[j]가 nums[k]이면 안됨
                        goodCnt++;
                        break;
                    }
                    else if(i==k)
                        i++;
                    else if(j==k)
                        j--;
                }
            }
        }

        bw.write(Integer.toString(goodCnt));
        bw.flush();
    }
}
