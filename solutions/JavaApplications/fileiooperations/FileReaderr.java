package FileIOs;
import java.io.*;
public class FileReaderr {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		
		try {
			FileReader fr=new FileReader("D:\\\\\\\\File io creater files\\\\\\\\FileWriterMethod.text");
	        int i;
			while((i=fr.read())!=-1)
				System.out.print((char)i);
			fr.close();
		}catch (Exception e){
			e.getStackTrace();
		}

	}

}
