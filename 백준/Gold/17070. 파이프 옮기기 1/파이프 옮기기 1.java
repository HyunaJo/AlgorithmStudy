import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int HORIZONTAL = 0; // 가로
    static final int VERTICAL = 1; // 세로
    static final int DIAGONAL = 2; // 대각선
    static final int BLANK = 0;
    static final int WALL = 1;

    static int N;
    static int[][] map;
    static int[][][] possibleCnt;
    static int ans;

    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        possibleCnt = new int[N+1][N+1][3];
        possibleCnt[1][2][HORIZONTAL] = 1;
        
        for(int i=1;i<=N;i++) {
        	for(int j=1;j<=N;j++) {
        		if(possibleCnt[i][j][HORIZONTAL]==0 && possibleCnt[i][j][VERTICAL]==0 && possibleCnt[i][j][DIAGONAL]==0) {
        			continue;
        		}
        		
        		if(isPossible(i, j+1)) {
        			possibleCnt[i][j+1][HORIZONTAL] += possibleCnt[i][j][HORIZONTAL];
        			possibleCnt[i][j+1][HORIZONTAL] += possibleCnt[i][j][DIAGONAL];
        		}
        		
        		if(isPossible(i+1, j)) {
        			possibleCnt[i+1][j][VERTICAL] += possibleCnt[i][j][VERTICAL];
        			possibleCnt[i+1][j][VERTICAL] += possibleCnt[i][j][DIAGONAL];
        		}
        		
        		if(isPossible(i, j+1) && isPossible(i+1, j) && isPossible(i+1, j+1)) {
        			possibleCnt[i+1][j+1][DIAGONAL] += possibleCnt[i][j][HORIZONTAL];
        			possibleCnt[i+1][j+1][DIAGONAL] += possibleCnt[i][j][VERTICAL];
        			possibleCnt[i+1][j+1][DIAGONAL] += possibleCnt[i][j][DIAGONAL];
        		}
        	}
        }
        
        
        for(int i=0;i<3;i++){
            ans += possibleCnt[N][N][i];
        }
        System.out.println(ans);
    }

    public static boolean isPossible(int x, int y) {
    	if(x<1 || x>N || y<1 || y>N) {
    		return false;
    	}
    	
    	if(map[x][y] == 1) {
    		return false;
    	}
    	
    	return true;
    }
}
