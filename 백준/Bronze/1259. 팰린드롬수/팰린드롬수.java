import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            String input = br.readLine();
            if(input.equals("0")){
                break;
            }

            boolean isPalindrome = true;
            int inputLength = input.length();
            int checkLength = inputLength/2;
            for(int i=0;i<checkLength;i++){
                if(input.charAt(i) != input.charAt(inputLength-1-i)){
                    isPalindrome = false;
                    break;
                }
            }

            sb.append((isPalindrome)?"yes":"no").append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
