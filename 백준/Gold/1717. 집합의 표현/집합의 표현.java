import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int n,m;
    public static int[] nums;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n+1];
        for(int i=0;i<=n;i++)
            nums[i] = i;

        while(m>0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd == 0){ // 합집합
                union(a,b);
            }
            else if(cmd == 1){ // 포함 확인
                if(findParent(a)==findParent(b))
                    bw.write("YES\n");
                else
                    bw.write("NO\n");
            }
            m--;
        }

        bw.flush();

        br.close();
        bw.close();
    }

    public static void union(int a, int b){
        if(a==b)
            return;

        a = findParent(a);
        b = findParent(b);

        if(a>b)
            nums[a] = b;
        else
            nums[b] = a;
    }

    public static int findParent(int a){
        if(nums[a]==a)
            return a;

        return nums[a] = findParent(nums[a]);
    }
}
