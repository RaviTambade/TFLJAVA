package FileIOs;
import java.io.*;
public class FileWriterr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileWriter fw =new FileWriter("D:\\\\File io creater files\\\\FileWriterMethod.text");
			fw.write("Welcome to java");
			fw.write("\n Welcome to Transflower Learning Platform");
			fw.close();
	
		}catch(IOException e) {
			e.getMessage();
		}
		System.out.println("Writing Successfully...");
	}

}
