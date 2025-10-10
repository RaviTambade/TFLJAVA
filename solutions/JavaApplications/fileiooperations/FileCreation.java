package FileIOs;
import java.io.*;
public class FileCreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			File file=new File("D:\\File io creater files\\FileOutputStream.text");
			if(file.createNewFile()) {
				System.out.println("New File is Created");
			}
			else {
				System.out.println("File Already Existed");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
