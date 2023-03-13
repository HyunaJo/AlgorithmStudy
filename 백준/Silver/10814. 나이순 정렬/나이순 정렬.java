import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		Person [] people = new Person[n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			people[i] = new Person(Integer.parseInt(st.nextToken()),st.nextToken());
		}
		
		Arrays.sort(people);
		for(int i=0;i<n;i++) {
			bw.write(people[i].print()+"\n");
		}
		bw.flush();
	}
}

class Person implements Comparable<Person>{
	int age;
	String name;
	
	Person(int age, String name){
		this.age = age;
		this.name = name;
	}
	
	public String print() {
		return Integer.toString(age)+" "+name;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		if(this.age==o.age)
			return 0; // 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서
		else
			return this.age-o.age; // 나이는 증가하는 순
	}
}
