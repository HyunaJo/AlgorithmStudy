import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 학생의 수
        ArrayList<Student> students = new ArrayList<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            students.add(new Student(st.nextToken(),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        Collections.sort(students);
        for(int i=0;i<N;i++)
            bw.write(students.get(i).name+"\n");
        bw.flush();
    }

    public static class Student implements Comparable<Student>{
        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math){
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            if(this.korean == o.korean) { // 국어 점수 같은 경우
                if(this.english == o.english){ // 영어 점수 같은 경우
                    if(this.math == o.math){
                        return this.name.compareTo(o.name); // 이름 비교
                    }
                    else
                        return o.math - this.math; // 수학 내림차순 정렬
                }
                else
                    return this.english - o.english; // 영어 오름차순 정렬
            }
            else
                return o.korean - this.korean; // 국어 내림차순 정렬
        }
    }
}
