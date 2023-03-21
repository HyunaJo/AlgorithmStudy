import java.io.*;
import java.util.*;

// 정렬 - q2108(통계학)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 수의 개수 N (홀수)
        ArrayList<Integer> nums = new ArrayList<>();
        Map<Integer,Integer> cntMap = new HashMap<>();

        long total = 0; // 산술평균을 위한 값들의 총 합
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());
            nums.add(num);
            total += (long)num;
            if(cntMap.containsKey(num))
                cntMap.put(num, cntMap.get(num)+1);
            else
                cntMap.put(num,1);
        }
        Collections.sort(nums);
        bw.write(Integer.toString((int)Math.round(total/(double)N))+"\n"); // 산술평균
        bw.write(Integer.toString(nums.get(N/2))+"\n"); // 중앙값

        // 최빈값
        ArrayList<Integer> maxNums = new ArrayList<>();
        int maxCnt = 0;
        Iterator<Integer> iter = cntMap.keySet().iterator();
        while(iter.hasNext()){
            int key = iter.next();
            int keyCnt = cntMap.get(key);
            if(maxCnt<keyCnt){
                maxNums.clear();
                maxNums.add(key);
                maxCnt = keyCnt;
            }
            else if(maxCnt==keyCnt) {
                maxNums.add(key);
            }
        }

        int maxNum = 0;
        Collections.sort(maxNums);
        if(maxNums.size()>1)
            maxNum = maxNums.get(1);
        else
            maxNum = maxNums.get(0);

        bw.write(Integer.toString(maxNum)+"\n");
        bw.write(Integer.toString(nums.get(N-1)-nums.get(0))); // 범위
        bw.flush();
    }
}