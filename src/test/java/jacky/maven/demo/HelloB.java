package jacky.maven.demo;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-02-02 10:43
 * @since jdk1.8
 */
class HelloA {

	// 3����ִ�л���/���๹�캯������������
	public HelloA() {
		System.out.println("HelloA");
	}

	// 2����ִ�л���/������빹���
	{
		System.out.println("I'm A class");
	}

	// 1����ִ�л���/����static�������
	static {
		System.out.println("static A");
	}

}

/**
 * ��1�������֮�󣬰����ϵ��£��Ӹ��ൽ���ִࣩ�б�static���ε���䣻��2����static���ִ����֮��,��ִ��main��������3����������new������Ķ��󣬽����ϵ���ִ�й������顢�����������߿���˵����һ�𣩡�
 * 
 * @author huang
 *
 */
public class HelloB extends HelloA {
	public HelloB() {
		System.out.println("HelloB");
	}

	{
		System.out.println("I'm B class");
	}

	static {
		System.out.println("static B");
	}

	public static void main(String[] args) {

		System.out.println("-------main start-------");

		// 2����ִ��main����������new���󣬽����ϵ���ִ�й������顢������
		new HelloB();
		new HelloB();
		System.out.println("-------main end-------");
	}
}