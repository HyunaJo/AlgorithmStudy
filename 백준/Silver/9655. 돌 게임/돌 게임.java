import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N;
    
    public static void main(String[] arg) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N =  Integer.parseInt(br.readLine());
        if(N%2 == 0){
            bw.write("CY");
        }
        else{
            bw.write("SK");
        }
        bw.flush();
        
        br.close();
        bw.close();
    }
}
