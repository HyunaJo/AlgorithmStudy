import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int N; // 가로칸 수
    static int[][] map;
    static ArrayList<Integer> findNums = new ArrayList<>(); // 찾은 수들
    static ArrayList<Integer> completeIdx = new ArrayList<>(); // 가능 판단된 수들

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[2][N+1];

        for(int i=1;i<=N;i++){
            int num = Integer.parseInt(br.readLine());
            map[0][i] = i;
            map[1][i] = num;
            if(num == i) {// 첫째 줄과 둘째 줄 값 같은 경우
                completeIdx.add(i);
            }
        }

        for(int i=1;i<=N;i++){
            findNums.clear();
            if(!completeIdx.contains(i)) {
                if(dfs(i,i)){
                    completeIdx.addAll(findNums);
                }
            }
        }

        Collections.sort(completeIdx);
        bw.write(Integer.toString(completeIdx.size())+"\n");
        for(int idx:completeIdx)
            bw.write(Integer.toString(idx)+"\n");
        bw.flush();
    }

    public static boolean dfs(int num, int targetNum){
        findNums.add(num);

        int newNum = map[1][num];
        if(newNum == targetNum)
            return true;
        else if(completeIdx.contains(newNum) || findNums.contains(newNum))
            return false;
        else
            return dfs(newNum,targetNum);
    }
}
