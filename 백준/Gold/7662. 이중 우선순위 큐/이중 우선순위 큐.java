import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static int T; // 테스트케이스의 수

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        while(T-->0){
            int k = Integer.parseInt(br.readLine()); // 연산 개수
            TreeMap<Integer,Integer> nums = new TreeMap<>();

            // Q에 대한 연산 수행
            while(k-->0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0); // 연산 유형
                int num = Integer.parseInt(st.nextToken()); // 유형 이후 숫자

                if (type == 'I') { // 삽입인 경우
                    if (nums.containsKey(num)) {
                        nums.put(num, nums.get(num) + 1);
                    } else {
                        nums.put(num, 1);
                    }
                }
                else if (type == 'D') { // 삭제인 경우
                    if(nums.keySet().isEmpty()){
                        continue;
                    }

                    int removedNum = 0;
                    if (num == 1) { // 최댓값 삭제
                        removedNum = nums.lastKey();
                    }
                    else { // 최솟값 삭제
                        removedNum = nums.firstKey();
                    }

                    int numCnt = nums.get(removedNum); // 제거한 값의 기존 개수
                    if (numCnt == 1) { // 1개인 경우
                        nums.keySet().remove(removedNum); // TreeMap에서 제거
                    }
                    else { // 2개 이상인 경우
                        nums.put(removedNum, numCnt - 1); // TreeMap에서 개수 줄이기
                    }
                }
            }

            if(nums.keySet().isEmpty()) { // 빈 경우
                bw.write("EMPTY\n");
            }
            else {
                bw.write(Integer.toString(nums.lastKey())+" "+Integer.toString(nums.firstKey())+"\n");
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
