import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static int N; // 수열의 크기
    public static int[] nums; // 입력값
    public static int[] lisIdx; // LIS에서의 index
    public static ArrayList<Integer> LIS = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // 입력 받기
        nums = new int[N+1];
        lisIdx = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // lis 갱신
        // 첫번째 값은 기본으로 넣어두고 시작
        LIS.add(nums[1]);
        lisIdx[1] = 0;
        for(int i=2;i<=N;i++){
            int idx = findLisIdx(nums[i]);
            lisIdx[i] = idx;
        }

        int lisSize = LIS.size();
        bw.write(Integer.toString(lisSize)+"\n");

        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=N;i>0;i--){
            if(lisSize == 0)
                break;
            if(lisIdx[i] == lisSize-1){
                deque.add(nums[i]);
                lisSize--;
            }
        }

        while(!deque.isEmpty()){
            bw.write(Integer.toString(deque.pollLast())+" ");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static int findLisIdx(int num){
        if(num>LIS.get(LIS.size()-1)) { // LIS의 마지막 값보다 큰 경우
            LIS.add(num); // LIS 마지막에 추가
            return LIS.size()-1;
        }

        // LIS에서 들어가게 될 인덱스 값 찾기 (이분탐색)
        int start = 0;
        int end = LIS.size()-1;
        int mid = 0;
        while(start<end){
            mid = (start+end)/2;

            int tmp = LIS.get(mid);
            if(tmp>=num){ // num(넣을 값)이 tmp(이미 들어가있는 값)보다 작거나 같은 경우
                end = mid; // 삽입 위치 앞에서 찾기
            }
            else{ // num(넣을 값)이 tmp(이미 들어가있는 값)보다 큰 경우
                start = mid + 1; // 삽입 위치 뒤에서 찾기
            }
        }

        LIS.set(start,num);
        return start;
    }
}
