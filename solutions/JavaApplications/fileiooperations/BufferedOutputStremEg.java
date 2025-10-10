package FileIOs;
import java.io.*;
public class BufferedOutputStremEg {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			File file=new File("D:\\File io creater files\\BufferOutputStream.text");
			if(file.createNewFile()) {
				System.out.println("New File is Created");
			}
			else {
				System.out.println("File Already Existed");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		FileOutputStream fout=new FileOutputStream("D:\\\\File io creater files\\\\BufferOutputStream.text");
		
		BufferedOutputStream bout = new BufferedOutputStream(fout);
		String s="Welcomeee to the buffer reading \nAlso in Transflowewr"
			   + " 2562 is your id";
		//converting String into byte's
		byte b[]=s.getBytes();
		bout.write(b);
		bout.flush();
		bout.close();
		fout.close();
		System.out.println("Success...");
		
        
	}

}
