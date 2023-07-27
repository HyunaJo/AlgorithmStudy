import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    public static int N;
    public static ArrayList<Integer> primes = new ArrayList<>();
    public static int ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        
        if(N!=1){
            for(int i=2;i<=N;i++){
                if(isPrime(i))
                    primes.add(i);
            }

            findSum();
            
        }
        bw.write(Integer.toString(ans));
        
        br.close();
        bw.close();
    }

    public static boolean isPrime(int num){ // 소수인지 판단
        for(int i=2;i<=(int)Math.sqrt(num);i++){
            if(num%i == 0)
                return false;
        }
        return true;
    }

    public static void findSum(){
        int lp = 0;
        int rp = 0;
        int sum = primes.get(rp);

        while(lp<=rp){
            if(sum<N){ // 소수의 합이 N보다 작은 경우
                rp++;
                if(rp>=primes.size())
                    return;
                sum+=primes.get(rp);
            }
            else if(sum>N){ // 소수의 합이 N보다 큰 경우
                sum -= primes.get(lp);
                lp++;
            }
            else{ // 소수의 합이 N인 경우
                ans++;
                sum -= primes.get(lp);
                lp++;
                rp++;
                if(rp>=primes.size())
                    return;
                sum += primes.get(rp);
            }
        }
    }
}
