import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
    static final int TESTCASE = 10;
    static final int MAX = 100;

    static int N, start;
    static HashMap<Integer, HashSet<Integer>> edges;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int t=1;t<=TESTCASE;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            edges = new HashMap<>();
            for(int i=0;i<N;i+=2){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                if(!edges.containsKey(from)){
                    edges.put(from, new HashSet<>());
                }

                edges.get(from).add(to);
            }

            visited = new boolean[MAX+1];
            sb.append("#").append(t).append(" ").append(findLastMax(start)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static int findLastMax(int num){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(num);
        visited[num] = true;

        int maxNum = start;
        while(!dq.isEmpty()){
            int dqSize = dq.size();

            maxNum = 0;
            while(dqSize-->0){
                int now = dq.poll();
                maxNum = Math.max(maxNum,now);

                if(!edges.containsKey(now)){
                    continue;
                }

                HashSet<Integer> nexts = edges.get(now);
                for(int next:nexts){
                    if(visited[next]){
                        continue;
                    }
                    visited[next] = true;
                    dq.add(next);
                }
            }
        }

        return maxNum;
    }
}
