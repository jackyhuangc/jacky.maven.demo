package jacky.maven.demo;

/**
 * ������ʵ��!
 * 
 * @author Jacky Huang
 * @date 2018-01-29 16:05
 * @since jdk1.8
 */
public class Customer implements Runnable {

	private int neednum; // ��������
	private Godown godown; // ��Ʒ�ֿ�

	Customer(int neednum, Godown godown) {
		this.neednum = neednum;
		this.godown = godown;
	}

	@Override
	public void run() {

		while (true) {
			// ����ָ��������Ʒ
			this.godown.consume(this.neednum);

			// ����ϵ�����
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
