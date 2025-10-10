package FileIOs;
import java.io.*;
public class FileOutputStreamEg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			FileOutputStream  fout=new FileOutputStream ("D:\\File io creater files\\FileOutputStream.text");
		    String s="Welcome to Transflower Training World";
		    byte b[]=s.getBytes();//Converting string into byte		
		    fout.write(b);
		    fout.close();
		    System.out.println("Success...");

		}catch(IOException e) {
		   e.getMessage();
		}
		
	}

}
