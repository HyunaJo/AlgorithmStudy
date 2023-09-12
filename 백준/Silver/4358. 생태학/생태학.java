import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static HashMap<String,Integer> trees = new HashMap<>();
    public static int totalCnt = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String type = "";
        while((type=br.readLine())!=null && !type.isEmpty()){
            totalCnt++;

            if(trees.containsKey(type)){ // 한번 입력된 종인 경우
                trees.put(type, trees.get(type)+1);
            }
            else{ // 처음 입력된 종인 경우
                trees.put(type,1);
            }
        }

        ArrayList<String> typeNames = new ArrayList<>(trees.keySet());
        Collections.sort(typeNames);
        for(String typeName:typeNames){
            Double typePercent = (double)(trees.get(typeName)*100)/(double)totalCnt;
            bw.write(typeName+" "+String.format("%.4f",typePercent)+'\n');
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
