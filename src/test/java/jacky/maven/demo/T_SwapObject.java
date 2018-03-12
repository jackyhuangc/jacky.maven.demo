package jacky.maven.demo;

public class T_SwapObject {

	private String name;
	private String sex;

	public T_SwapObject(String x, String y) {
		this.name = x;
		this.sex = y;
	}

	public String toString() {
		return name + " " + sex;
	}

	public static void swapObject(T_SwapObject t1, T_SwapObject t2) {
		T_SwapObject tmp = t1;
		t1 = t2;
		t2 = tmp;
	}

	public static void changeObject(T_SwapObject t1, T_SwapObject t2) {

		// �������ܸı��κζ�������Ϊjava�����ô��ݸ�����ݵ��ǵ�ַ
		T_SwapObject tmp = t1;
		t1 = t2;
		t2 = tmp;

		// �����Ըı��ڲ������ݻ�ֵ
		t1.name = "xxxxxxxxxx";
	}

	public static void swapObjectArray(T_SwapObject[] t1, T_SwapObject[] t2) {
		T_SwapObject[] tmp = t1;
		t1 = t2;
		t2 = tmp;
	}

	public static void changeObjectArray(T_SwapObject[] t1, T_SwapObject[] t2) {
		T_SwapObject[] tmp = t1;
		t1 = t2;
		t2 = tmp;

		t1[0].name = "test";
	}

	public static void main(String args[]) {

		T_SwapObject t1 = new T_SwapObject("abc", "fale");
		T_SwapObject t2 = new T_SwapObject("def", "male");
		System.out.println("ת��ǰ��" + t1.toString());
		System.out.println("ת��ǰ��" + t2.toString());
		T_SwapObject.swapObject(t1, t2);
		System.out.println("ת����" + t1.toString());
		System.out.println("ת����" + t2.toString());

		T_SwapObject.changeObject(t1, t2);
		System.out.println("ת����" + t1.toString());
		System.out.println("ת����" + t2.toString());

		T_SwapObject[] arrays1 = new T_SwapObject[2];
		arrays1[0] = new T_SwapObject("a1", "1");
		arrays1[1] = new T_SwapObject("a2", "2");

		T_SwapObject[] arrays2 = new T_SwapObject[2];
		arrays2[0] = new T_SwapObject("b1", "1");
		arrays2[1] = new T_SwapObject("b2", "2");

		System.out.println(arrays1[0].toString() + " " + arrays1[1].toString());
		System.out.println(arrays2[0].toString() + " " + arrays2[1].toString());
		T_SwapObject.swapObjectArray(arrays1, arrays2);

		System.out.println(arrays1[0].toString() + " " + arrays1[1].toString());
		System.out.println(arrays2[0].toString() + " " + arrays2[1].toString());

		System.out.println(arrays1[0].toString() + " " + arrays1[1].toString());
		System.out.println(arrays2[0].toString() + " " + arrays2[1].toString());
		T_SwapObject.changeObjectArray(arrays1, arrays2);

		System.out.println(arrays1[0].toString() + " " + arrays1[1].toString());
		System.out.println(arrays2[0].toString() + " " + arrays2[1].toString());
	}
}
