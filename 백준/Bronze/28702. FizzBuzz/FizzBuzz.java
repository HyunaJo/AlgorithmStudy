import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int order = 0;
        int num = 0;
        for(int i=1;i<=3;i++){
            String input = br.readLine();
            if(!input.contains("Fizz") && !input.contains("Buzz")){
                order = i;
                num = Integer.parseInt(input);
            }
        }

        int ansNum = num + (4-order);
        if(ansNum % 15 == 0){
            bw.write("FizzBuzz");
        }
        else if(ansNum % 3 == 0){
            bw.write("Fizz");
        }
        else if(ansNum % 5 == 0){
            bw.write("Buzz");
        }
        else{
            bw.write(Integer.toString(ansNum));
        }
        bw.flush();
    }
}
