import java.io.*;
import java.util.*;

// 자료구조 - q1966 (프린터 큐)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for(int i=0;i<testcase;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 문서의 개수 N
            int M = Integer.parseInt(st.nextToken()); // 몇 번째로 인쇄되었는지 궁금한 문서가 현재 큐에서 몇 번째인지
            int printOrder = 1;

            ArrayList<Integer> importances = new ArrayList<>(); // 중요도들
            Deque<Integer> queue = new ArrayDeque<>(); // 프린터에 담긴 중요도
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                int importance = Integer.parseInt(st.nextToken());
                importances.add(importance);
                queue.add(importance); // 큐 채우기
            }

            Collections.sort(importances, Collections.reverseOrder()); // 중요도 오름차순 정렬
            int importanceIdx = 0;
            while(!queue.isEmpty()){
                int printImportance = queue.removeFirst();
                if(printImportance!=importances.get(importanceIdx)) {
                    queue.addLast(printImportance);
                    if(M==0)
                        M = queue.size()-1;
                    else
                        M--;
                }
                else {
                    if(M==0) {
                        bw.write(Integer.toString(printOrder)+"\n");

                        break;
                    }

                    importanceIdx++;
                    printOrder++;
                    M--;
                }
            }
        }
        bw.flush();
    }
}
