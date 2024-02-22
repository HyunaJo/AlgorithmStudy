import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] populations;
    static ArrayList<Integer>[] edges;
    static boolean[] selected;
    static int totalPopulation = 0;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 구역의 개수


        populations = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            populations[i] = Integer.parseInt(st.nextToken());
            totalPopulation += populations[i];
        }

        edges = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            edges[i] = new ArrayList<>();
        }
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for(int j=0;j<cnt;j++) {
                edges[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        selected = new boolean[N+1];
        findArea(1);

        bw.write((ans==Integer.MAX_VALUE)?"-1":Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findArea(int idx) {
        if(idx == N+1) {
            ArrayList<Integer> areaA = new ArrayList<>();
            ArrayList<Integer> areaB = new ArrayList<>();

            for(int i=1;i<=N;i++) {
                if(selected[i]) {
                    areaA.add(i);
                    continue;
                }

                areaB.add(i);
            }

            if(areaA.size()==0 || areaB.size()==0) {
                return;
            }

            if(isConnected(areaA) && isConnected(areaB)) {
                ans = Math.min(ans, caculateDiff(areaA));
            }

            return;
        }

        selected[idx] = true;
        findArea(idx+1);

        selected[idx] = false;
        findArea(idx+1);
    }

    public static boolean isConnected(ArrayList<Integer> area) {
        int start = area.get(0);
        boolean[] visited = new boolean[N+1];

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(start);
        visited[start] = true;

        int cnt = 0;
        while(!dq.isEmpty()) {
            int now = dq.poll();
            cnt++;

            ArrayList<Integer> nexts = edges[now];
            for(int next:nexts) {
                if(visited[next]) {
                    continue;
                }

                if(area.contains(next)) {
                    visited[next] = true;
                    dq.add(next);
                }
            }
        }

        return cnt == area.size();
    }

    public static int caculateDiff(ArrayList<Integer> areaA) {
        int populationA = 0;
        for(int num:areaA) {
            populationA += populations[num];
        }

        int populationB = totalPopulation - populationA;

        return Math.abs(populationA-populationB);
    }
}

