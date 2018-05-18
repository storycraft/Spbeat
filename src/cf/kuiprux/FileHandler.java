package cf.kuiprux;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

	public String readFile(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();
		
		String str;
		while((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\r\n");
		}
		br.close();
		
		return sb.toString();
	}
	
	public void writeFile(String path, String content, boolean override) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), override));
		bw.write(content);
		bw.flush();
		bw.close();
	}
}
