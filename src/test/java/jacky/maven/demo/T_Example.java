package jacky.maven.demo;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-02-02 10:52
 * @since jdk1.8
 */
public class T_Example {

	String str = new String("good");

	char[] ch = { 'a', 'b', 'c' };

	public static void main(String args[]) {

		T_Example ex = new T_Example();

		ex.change(ex.str, ex.ch);

		System.out.print(ex.str + " and ");

		System.out.print(ex.ch);

	}

	public void change(String str, char ch[]) {

		// �滻�޷��ı���������
		str = "test ok";
		
		// ���Ըı��������
		ch[0] = 'g';

	}
}
