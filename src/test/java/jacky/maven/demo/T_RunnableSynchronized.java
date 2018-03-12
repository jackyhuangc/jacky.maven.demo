package jacky.maven.demo;

import java.io.IOException;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-01-29 16:10
 * @since jdk1.8
 */
public class T_RunnableSynchronized {

	public static Object lock = new Object();
	public static int Counter = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Godown godown = new Godown(30);
		SuperMarket s = new SuperMarket(10, godown);
		new Thread(s).start();

		System.out.println("");
		Customer c = new Customer(50, godown);
		new Thread(c).start();

		try {
			throw new Exception("xxx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IOException");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception");
		}

		int result = getValue(2);

		System.out.println(result);
	}

	public static int getValue(int i) {
		int result = 0;

		switch (i) {

		case 1:
			result = result + i * 1;
		case 2:
			result = result + i * 2;
			// 没有break,代码将从此处开始继续往下执行
		case 3:
			result = result + i * 3;
		}

		return result;
	}
}
