package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {
	
	public static boolean printToFile(String data) {
		String fileName = "invitees.txt";
		File file = new File(fileName);
		String absPath = file.getAbsolutePath();
		if(!file.exists()){
			try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				fw.write(data);
				fw.flush();
				fw.close();
				System.out.println("\n\nData has been saved to " + fileName + "\n"
						+ "located at " + absPath);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
