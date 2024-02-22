import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static final int FRIEND_DEPTH = 4; // 친구관계 depth가 4이상이어야 함
	static int N, M;
	static ArrayList<Integer>[] relations; // 친구 관계
	static boolean[] visited; // 확인 여부
	static boolean isExisted = false; // 친구 관계 존재하는지

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 친구 관계의 수
		
		// 친구 관계 입력받기
		relations = new ArrayList[N];
		for(int i=0;i<N;i++) {
			relations[i] = new ArrayList<>(); 
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			relations[a].add(b);
			relations[b].add(a);
		}

		visited = new boolean[N]; // 확인 여부
		for(int i=0;i<N;i++) {
			visited[i] = true;
			checkExisted(i, 0); // 각각의 시작점에 대해 depth 확인
			visited[i] = false;
		}
		
		// 결과 출력
		bw.write((isExisted)?"1":"0");
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void checkExisted(int num, int depth) {
		if(isExisted) { // 이미 존재한다고 판단된 경우
			return;
		}
		
		if(depth == FRIEND_DEPTH) { // depth가 친구관계 만족한 경우
			isExisted = true;
			return;
		}
		
		for(int next:relations[num]) { // 친구 관계인 사람 파고들어가기
			if(visited[next]) { // 이미 방문한 사람인 경우
				continue;
			}
			
			// 방문하지 않은 사람인 경우
			visited[next] = true;
			checkExisted(next, depth+1);
			visited[next] = false;
		}
	}

}
