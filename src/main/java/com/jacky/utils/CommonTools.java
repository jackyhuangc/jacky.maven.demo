package com.jacky.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSON;
import com.jacky.mvc.entities.Menu;

/**
 * The common tools or functions etc.
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:56
 * @since jdk1.8
 */
public class CommonTools {

	public static String ReadFromFile(String filePath) {
		InputStreamReader isr;
		try {
			// URL url =
			// this.getClass().getClassLoader().getResource("menu.json");
			// String file = url.getFile();
			// 读取resources文件夹下的文件
			// Resource res2 = new ClassPathResource("conf/file1.txt");
			// isr = new
			// InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("menu.json"),
			// "UTF-8");
			isr = new InputStreamReader(new ClassPathResource(filePath).getInputStream(), "UTF-8");
			int bufferSize = 1024;
			char[] buffer = new char[bufferSize];
			StringBuilder out = new StringBuilder();
			for (;;) {
				int rsz = isr.read(buffer, 0, buffer.length);
				if (rsz < 0)
					break;
				out.append(buffer, 0, rsz);
			}

			isr.close();
			return out.toString();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

		} finally {
		}
		return null;
	}

	// 深拷贝
	public static <T> List<T> deepCopy(List<T> src) {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(byteOut);

			out.writeObject(src);

			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			@SuppressWarnings("unchecked")
			List<T> dest = (List<T>) in.readObject();

			return dest;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ArrayList<T>();
	}
}
