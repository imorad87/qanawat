import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) throws IOException {
		// Stream<String> fileContents = Files.lines(new
		// File("C:\\Users\\islam.morad\\Desktop\\script.js").toPath());
		FileInputStream in = new FileInputStream(
				"C:\\Users\\islam.morad\\Desktop\\script.js");
		Scanner s = new Scanner(in);
		StringBuilder x = new StringBuilder();
		StringBuffer bb = new StringBuffer();
		
		while (s.hasNext()) {

			String next = s.nextLine();
			x.append(next);
			
			x.append(System.lineSeparator());
			// System.out.println(next);
		}

		System.out.println(x.toString());
	}

}
