import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static class Applicant implements Comparable<Applicant>{
        int document;
        int interview;

        public Applicant(int document, int interview){
            this.document = document;
            this.interview = interview;
        }

        @Override
        public int compareTo(Applicant o){
            return this.document - o.document;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine()); // 지원자의 수

            ArrayList<Applicant> applicants = new ArrayList<>();
            for(int j=0;j<N;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                applicants.add(new Applicant(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
            }

            Collections.sort(applicants); // 순위 높은 순으로 정렬
            int minInterviewRank = applicants.get(0).interview; // minInterviewRank보다는 등수가 높아야함
            int passCnt = 1; // 합격한 지원자 수
            for(int j=1;j<N;j++){
                int interviewRank = applicants.get(j).interview;
                if(interviewRank<minInterviewRank) {
                    passCnt++;
                    minInterviewRank = interviewRank;
                }
            }
            bw.write(Integer.toString(passCnt)+"\n");
        }
        bw.flush();
    }

}
