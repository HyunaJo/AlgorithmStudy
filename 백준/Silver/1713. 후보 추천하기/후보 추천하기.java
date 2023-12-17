import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int N, totalCnt;
    public static PriorityQueue<Picture> pictures = new PriorityQueue<>();
    public static boolean[] existed = new boolean[101];

    public static class Picture implements Comparable<Picture>{
        int inputTime = 0;
        int studentNum = 0;
        int recommendCnt = 0;

        public Picture(int inputTime, int studentNum, int recommendCnt){
            this.inputTime = inputTime;
            this.studentNum = studentNum;
            this.recommendCnt = recommendCnt;
        }

        @Override
        public int compareTo(Picture o) {
            if(recommendCnt == o.recommendCnt){
                return this.inputTime - o.inputTime;
            }

            return this.recommendCnt - o.recommendCnt;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        totalCnt = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<totalCnt;i++){
            int studentNum = Integer.parseInt(st.nextToken());

            putPicture(i, studentNum);
        }

        for(int i=1;i<=100;i++){
            if(existed[i]){
                bw.write(i+" ");
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static void putPicture(int time, int studentNum){
        if(existed[studentNum]){
            Picture removedPicture = null;

            for(Picture picture:pictures){
                if(picture.studentNum == studentNum){
                    removedPicture = picture;
                    pictures.remove(picture);
                    break;
                }
            }

            pictures.add(new Picture(removedPicture.inputTime, studentNum, removedPicture.recommendCnt+1));
            return;
        }

        if(pictures.size() == N){
            Picture picture = pictures.poll();
            existed[picture.studentNum] = false;
        }

        pictures.add(new Picture(time, studentNum, 1));
        existed[studentNum] = true;
    }
}
