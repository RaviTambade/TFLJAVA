package FileIOs;
import java.io.*;


public class BufferedInputStreamEg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			FileInputStream fin =new FileInputStream("D:\\File io creater files\\BufferOutputStream.text");
			
			BufferedInputStream bin=new BufferedInputStream(fin);
			int i;
			while((i=bin.read())!=-1) {
				System.out.print((char)i);
			}
			bin.close();
			fin.close();
		}catch(Exception e) {
			System.out.println(e);
		}

	}

}
