package FileIOs;
import java.io.*;
public class FileInputStremeg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			FileInputStream  fin =new FileInputStream ("D:\\File io creater files\\FileOutputStream.text");
			int i=0;
			while((i=fin.read()) !=-1) {
				System.out.print((char)i);
				
			}
			fin.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}

	}

}
