import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N;
    public static StringBuilder result;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = "";
        while((input = br.readLine())!=null && !input.isEmpty()){
            N = Integer.parseInt(input);
            int totalCnt = (int)Math.pow(3,N); // '-' 총 개수 = 3^N개
            result = new StringBuilder();
            for(int i=0;i<totalCnt;i++){
                result.append("-");
            }

            makeResult(0, totalCnt-1);
            bw.write(result.toString()+"\n");
        }
        bw.flush();
        
        br.close();
        bw.close();
    }

    public static void makeResult(int start, int end){
        if(start==end){
            return;
        }

        int gap = (end-start+1)/3;
        makeResult(start,start+gap-1);

        for(int i=0;i<gap;i++){
            result.setCharAt(start+gap+i,' ');
        }


        makeResult(start+gap*2, end);
    }
}
