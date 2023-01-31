import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

// 정렬 - 단어 정렬
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt(); // 단어의 개수
		
		// 중복된 단어 담지 않기 위해 HashSet 사용
		HashSet<String> words = new HashSet<String>();
		for(int i=0;i<N;i++)
			words.add(scanner.next());
		
		ArrayList<String> list = new ArrayList<String>(words);
		// 단어 정렬
		Collections.sort(list, new Comparator<String>() {
			public int compare(String s1, String s2) {
				if(s1.length()==s2.length()) // 길이가 같으면 사전 순으로
					return s1.compareTo(s2);
				else // 길이가 짧은 것부터
					return s1.length()-s2.length();
			}
		});
		
		for(int i=0;i<list.size();i++)
			System.out.println(list.get(i));
	}
}
