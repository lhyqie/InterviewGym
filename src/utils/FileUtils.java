package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	/**
	 * read files as a list of Strings, lines starting with # are treated as comments and are thus ignored
	 * @param filepath : relative or absolute path of the file to be read
	 * @return a list of Strings each of which is a line
	 */
	public static List<String> readLines(String filepath){
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				//System.out.println(line);
				if(line.startsWith("#")) continue; // skip comments
				lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	private void test_readLines(){
		List<String> lines = FileUtils.readLines("data/palindrome_sents.txt");
		for (String line : lines) {
			System.out.println(line);
		}
	}
	
	public static void main(String[] args) {
		new FileUtils().test_readLines();
	}
}
