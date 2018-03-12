package jacky.maven.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-02-02 11:04
 * @since jdk1.8
 */
public class T_FileStreamTest {

	// throws������������������ܴ�����쳣( throws���ǳ�����һ������ͷ�У����������ó�Ա���������׳��ĸ����쳣)��
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// FileOutputStream���ֽ����������д�뺺�֣��������룬��Ϊһ��������2���ֽڣ��޷�һ��д��
		FileOutputStream out = new FileOutputStream("file.dat");

		// byte[] b = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// out.write(b);
		// out.close();

		// ���
		OutputStreamWriter oStreamWriter = new OutputStreamWriter(out, "utf-8");
		oStreamWriter.write("��Ϊһ��������2���ֽڣ��޷�һ��д��");
		oStreamWriter.close();

		FileInputStream in = new FileInputStream("file.dat");
		// in.skip(9); // ����ǰ���9���ֽ�
		// int c = in.read();
		// System.out.println(c); // ���Ϊ10
		// in.close();

		RandomAccessFile inR = new RandomAccessFile("file.dat", "r");
		inR.skipBytes(9);
		int c = inR.readByte();
		System.out.println(c); // ���Ϊ10
		inR.close();

		InputStreamReader oStreamReader = new InputStreamReader(in, "utf-8");
		StringBuffer sb = new StringBuffer();
		while (oStreamReader.ready()) {
			sb.append((char) oStreamReader.read());
		}

		System.out.print(sb.toString());
		oStreamReader.close();
		in.close();

	}

}
