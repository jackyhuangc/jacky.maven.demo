package jacky.maven.demo;

public class NULL {

	public void test() {
		System.out.print("test");
	}

	public static void haha() {
		System.out.println("haha");
	}

	public static void main(String[] args) {
		// ���Ϊhaha����Ϊnullֵ����ǿ��ת��Ϊ�κ�java������,(String)nullҲ�ǺϷ��ġ���nullǿ��ת��������Ч�����䷵��ֵ����Ϊnull����static�����ĵ����Ǻ������󶨵ģ�������������з�����������ȷ���
		((NULL) null).haha();
	}
}