import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static int n,m;
    // 성준이가 각 강의를 듣기 위해 신청해야하는 최소 마일리지
    public static PriorityQueue<Integer> possiblePoint = new PriorityQueue<>();
    public static int answer = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 과목 수
        m = Integer.parseInt(st.nextToken()); // 주어진 마일리지

        ArrayList<Integer> studentPoint = new ArrayList<>();
        int tmpN = n;
        while(tmpN-->0){ // 각 과목에 대해 성준이가 넣을 최소 마일리지 구하기
            studentPoint.clear(); // 초기화

            st = new StringTokenizer(br.readLine());
            int studentCnt = Integer.parseInt(st.nextToken()); // 신청한 학생 수
            int limitCnt = Integer.parseInt(st.nextToken()); // 과목 수강인원 수

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<studentCnt;i++){
                studentPoint.add(Integer.parseInt(st.nextToken()));
            }

            if(studentCnt<limitCnt){ // 신청한 학생 수 < 수강 인원
                possiblePoint.add(1); // 최소 마일리지만 넣기
            }
            else{ // 신청한 학생 수 >= 수강 인원
                // 수강 가능한 인원 중 가장 적게 넣은 학생만큼 마일리지 넣기
                Collections.sort(studentPoint,Comparator.reverseOrder());
                possiblePoint.add(studentPoint.get(limitCnt-1));
            }
        }

        int sum = 0; // 성준이가 신청할 마일리지 총합
        for(int i=0;i<n;i++){
            sum += possiblePoint.poll();

            if(sum>m){ // 신청할 마일리지 총합 > 성준이가 갖고 있는 총 마일리지
                break;
            }
            answer++;
        }

        bw.write(Integer.toString(answer));
        bw.flush();

        br.close();
        bw.close();
    }
}
