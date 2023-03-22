import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalComputer = scanner.nextInt();
		int connectNum = scanner.nextInt();
		
		boolean[] visited = new boolean[totalComputer];
		int[][] connections = new int[connectNum][2];
		for(int i=0;i<connectNum;i++) {
			connections[i][0] = scanner.nextInt();
			connections[i][1] = scanner.nextInt();
		}
		
//		System.out.println(countVirus(visited,1));
		countVirus(visited,connections,1);
//		System.out.println(visited);
		int sum=0;
		for(int i=1;i<totalComputer;i++) {
			if(visited[i])
				sum++;
		}
		System.out.println(sum);
	}
	
	public static void countVirus(boolean[] visited, int[][] connections, int computerNo) {
		visited[computerNo-1] = true;
		
//		System.out.println(computerNo+"->");
		for(int i=0;i<connections.length;i++) {
			if(connections[i][0]==computerNo && visited[connections[i][1]-1]==false) {
				visited[connections[i][1]-1]=true;
				countVirus(visited, connections, connections[i][1]);
			}
			else if(connections[i][1]==computerNo && visited[connections[i][0]-1]==false) {
				visited[connections[i][0]-1]=true;
				countVirus(visited, connections, connections[i][0]);
			}
		}
		
	}
}
