package jacky.maven.demo;

public class NULL {

	public void test() {
		System.out.print("test");
	}

	public static void haha() {
		System.out.println("haha");
	}

	public static void main(String[] args) {
		// 输出为haha，因为null值可以强制转换为任何java类类型,(String)null也是合法的。但null强制转换后是无效对象，其返回值还是为null，而static方法的调用是和类名绑定的，不借助对象进行访问所以能正确输出
		((NULL) null).haha();
	}
}