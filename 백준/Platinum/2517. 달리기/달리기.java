import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static int N;
    public static Runner[] runners;
    public static int[] bestRanks;
    public static int[] indexedTree;
    public static int offset = 1;

    public static class Runner implements Comparable<Runner>{
        long ability; // 실력
        int rank; // 현재 순위

        public Runner(long ability, int rank){
            this.ability = ability;
            this.rank = rank;
        }

        @Override
        public int compareTo(Runner o){ // 실력순으로 정렬 (내림차순)
            if(this.ability < o.ability)
                return 1;
            else if(this.ability > o.ability)
                return -1;
            else
                return 0;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // offset 설정
        while(offset<N){
            offset*=2;
        }

        runners = new Runner[N];
        bestRanks = new int[N];
        indexedTree = new int[offset*2+2];
        // 인덱스트리에 데이터 입력
        for(int i=0;i<N;i++){
            Runner newRunner = new Runner(Long.parseLong(br.readLine()), i+1);
            runners[i] = newRunner;
        }
        Arrays.sort(runners); // 실력 좋은 순으로 정렬

        // 인덱스트리 업데이트
        for(int i=0;i<N;i++){
            Runner runner = runners[i];
            update(runner.rank-1, 1);
            bestRanks[runner.rank-1] = sum(0,runner.rank-1);
        }

        for(int i=0;i<N;i++){
            bw.write(Integer.toString(bestRanks[i])+"\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static void update(int id, int value){
        int x = offset+id;
        indexedTree[x] = value;
        while(x > 0){
            x /= 2;
            indexedTree[x] = indexedTree[x*2] + indexedTree[x*2+1];
        }
    }

    public static int sum(int start, int end){
        int l = offset+start;
        int r = offset+end;

        int bestRank = 0;
        while(l<=r){
            if(l%2 == 1)
                bestRank += indexedTree[l++];
            if(r%2 == 0)
                bestRank += indexedTree[r--];
            l /= 2;
            r /= 2;
        }
        return bestRank;
    }
}
