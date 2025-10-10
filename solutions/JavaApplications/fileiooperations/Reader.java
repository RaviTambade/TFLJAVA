package FileIOs;
import java.io.*;
public class Reader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileReader reader=new FileReader("D:\\File io creater files\\ReaderMethod.text");
			int data = reader.read();
			while(data!=-1) {
				System.out.print((char)data);
				data=reader.read();
			}
			reader.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}
