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
            int printOrder = 1; // 프린트 되는 순서

            ArrayList<Integer> importances = new ArrayList<>(); // 중요도들
            Deque<Integer> queue = new ArrayDeque<>(); // 프린터에 담긴 중요도
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                int importance = Integer.parseInt(st.nextToken());
                importances.add(importance);
                queue.add(importance); // 큐 채우기
            }

            Collections.sort(importances, Collections.reverseOrder()); // 중요도 오름차순 정렬
            int importanceIdx = 0; // importances에 대한 인덱스 (pop한 후 add 안하는 경우 idx++)
            while(!queue.isEmpty()){ // 큐가 빌 때까지
                int printImportance = queue.removeFirst();
                if(printImportance!=importances.get(importanceIdx)) { // 중요도가 가장 큰 문서가 아닌 경우
                    queue.addLast(printImportance); // 다시 맨 뒤로
                    // 몇 번째 인쇄인지 궁금한 문서의 위치 변경
                    if(M==0) 
                        M = queue.size()-1;
                    else
                        M--;
                }
                else { // 중요도가 가장 큰 문서인 경우
                    if(M==0) { // 몇 번째 인쇄인지 궁금한 문서인 경우
                        bw.write(Integer.toString(printOrder)+"\n");
                        break;
                    }
                    // 순서 궁금한 문서 외 다른 문서인 경우
                    importanceIdx++;
                    printOrder++;
                    M--;
                }
            }
        }
        bw.flush();
    }
}
