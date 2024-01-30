import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; // 스위치 개수
	static int[] switches; // 스위치 상태
	static int studentCnt; // 학생 수
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 스위치 개수 입력받기
		switches = new int[N+1]; // 1번~N번이므로 N+1
		
		StringTokenizer st = new StringTokenizer(br.readLine()); // 스위치 상태 입력받기
		for(int i=1;i<=N;i++) {
			switches[i] = Integer.parseInt(st.nextToken()); // 스위치 상태 저장
		}
		
		studentCnt = Integer.parseInt(br.readLine()); // 학생 수 입력받기
		
		while(studentCnt-->0) { // 학생 수만큼 반복
			st = new StringTokenizer(br.readLine()); // 성별 + 받은 수
			int gender = Integer.parseInt(st.nextToken()); // 성별 입력받기
			int num = Integer.parseInt(st.nextToken()); // 받은 수 입력받기
			
			if(gender == 1) {
				// 1 : 남학생 -> 자기가 받은 수의 배수인 스위치 상태 변경
				changeBoySwitch(num);
			}
			else {
				// 2 : 여학생 -> 본인 스위치 중심으로 좌우 대칭이면서 가장 많은 스위치 포함하는 구간 내 스위치 상태 변경
				changeGirlSwitch(num);
			}
		}
		
		// 스위치 상태 출력
		for(int i=1;i<=N;i++) {
			bw.write(switches[i]+" ");
			
			// 한 줄에 스위치 20개씩 출력
			if(i%20 == 0) {  // 20 배수일 때 개행문자 추가
				bw.write("\n");
			}
		}
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void changeBoySwitch(int num) {
		// 배수인 스위치 변경
		for(int i=num;i<=N;i+=num) {
			changeSwitch(i);
		}
	}
	
	public static void changeGirlSwitch(int num) {
		// num 기준 좌우 대칭이면서 가장 많은 스위치 포함하는 구간 내 스위치 상태 변경
		changeSwitch(num); // 본인 스위치 변경
		
		int moveDist = 1; // num으로부터 떨어진 정도
		while(true) {
			int left = num-moveDist;
			int right = num+moveDist;
			
			if(left<1 || right>N) { // 1번~N번 밖이면 끝
				break;
			}
			
			if(switches[left] != switches[right]) { // 다른 상태인 경우
				break;
			}
			
			// 같은 상태인 경우 상태 변경
			changeSwitch(left);
			changeSwitch(right);
			moveDist++; // 한칸 더 떨어진 구간 확인용
		}
		
	}
	
	public static void changeSwitch(int idx) {
		// off(0) -> on(1)
		if(switches[idx] == 0) {
			switches[idx] = 1;
			return;
		}
		
		// on(1) -> off(0)
		switches[idx] = 0;
	}

}
