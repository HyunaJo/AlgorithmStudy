import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

class Solution {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			char[] memory = br.readLine().toCharArray();
			
			int changeCnt = 0;
			if(memory[0] == '1') {
				changeCnt++;
			}
			for(int i=1;i<memory.length;i++) {
				if(memory[i-1] != memory[i]) {
					changeCnt++;
				}
			}
			
			bw.write("#"+test_case+" "+changeCnt+"\n");
		}
		bw.flush();
		
		br.close();
		bw.close();
	}

}
