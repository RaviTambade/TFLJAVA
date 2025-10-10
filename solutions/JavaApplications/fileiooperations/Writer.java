package FileIOs;
import java.io.*;

public class Writer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		try {
			FileWriter w=new FileWriter("D:\\File io creater files\\WriteMethod.text");
			String content="Welcome to Transflower";
			w.write(content);
			w.close();
			System.out.println("Done");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Writing Successfully");

	}

}
