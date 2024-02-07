import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,M,R;
    static int[][] nums; // 배열
    static StringBuilder sb = new StringBuilder(); // 결과

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행 개수 입력받기
        M = Integer.parseInt(st.nextToken()); // 열 개수 입력받기
        R = Integer.parseInt(st.nextToken()); // 연산의 수 입력받기

        // 배열 입력받기
        nums = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine()); // 수행해야 하는 연산
        while(R-->0) { // 연산의 수만큼 연산 수행
            // 연산 번호에 따른 연산 수행
            int cmd = Integer.parseInt(st.nextToken()); // 연산 번호
            switch (cmd) {
                case 1:
                    upDown();
                    break;
                case 2:
                    leftRight();
                    break;
                case 3:
                    rotateRight();
                    break;
                case 4:
                    rotateLeft();
                    break;
                case 5:
                    changeRight();
                    break;
                case 6:
                    changeLeft();
                    break;
            }
        }

        // 결과 작성
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                sb.append(nums[i][j]).append(" ");
            }
            sb.append("\n");
        }
        // 결과 출력
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void upDown() {
        int changedCnt = N>>1; // 바꿔야하는 횟수
        for(int i=0;i<changedCnt;i++) {
            int[] tmp = nums[i]; // 임시로 담아두기
            nums[i] = nums[N-i-1];
            nums[N-i-1] = tmp;
        }
    }

    public static void leftRight() {
        int changedCnt = M>>1; // 바꿔야하는 횟수

        for(int i=0;i<N;i++) {
            for(int j=0;j<changedCnt;j++) {
                int tmp = nums[i][j];
                nums[i][j] = nums[i][M-j-1];
                nums[i][M-j-1] = tmp;
            }
        }
    }

    public static void rotateRight() {
        int[][] rotatedNums = new int[M][N];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                rotatedNums[j][N-i-1] = nums[i][j];
            }
        }

        nums = rotatedNums;
        int tmp = N;
        N = M;
        M = tmp;
    }

    public static void rotateLeft() {
        int[][] rotatedNums = new int[M][N];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                rotatedNums[M-j-1][i] = nums[i][j];
            }
        }

        nums = rotatedNums;
        int tmp = N;
        N = M;
        M = tmp;
    }

    public static void changeRight() {
        int halfN = N>>1;
        int halfM = M>>1;

        int[][] changedNums = new int[N][M];
        for(int i=0;i<halfN;i++){
            for(int j=0;j<halfM;j++){
                changedNums[i][halfM+j] = nums[i][j]; // 1->2
                changedNums[halfN+i][halfM+j] = nums[i][halfM+j]; // 2->3
                changedNums[halfN+i][j] = nums[halfN+i][halfM+j]; // 3->4
                changedNums[i][j] = nums[halfN+i][j]; // 4->1
            }
        }

        nums = changedNums;
    }

    public static void changeLeft() {
        int halfN = N>>1;
        int halfM = M>>1;

        int[][] changedNums = new int[N][M];
        for(int i=0;i<halfN;i++){
            for(int j=0;j<halfM;j++){
                changedNums[i][j] = nums[i][halfM+j]; // 2->1
                changedNums[i][halfM+j] = nums[halfN+i][halfM+j]; // 3->2
                changedNums[halfN+i][halfM+j] = nums[halfN+i][j]; // 4->3
                changedNums[halfN+i][j] = nums[i][j]; // 1->4
            }
        }

        nums = changedNums;
    }

}

