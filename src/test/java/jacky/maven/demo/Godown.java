package jacky.maven.demo;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-01-29 16:52
 * @since jdk1.8
 */
public class Godown {

	public static final int max_size = 100;// �������
	public int curnum;// ��ǰ�����

	Godown(int curnum) {
		this.curnum = curnum;// ��ʼ�����
	}

	// ��Java�����У�ÿһ��������һ�������߳̿���ʹ��synchronized�ؼ�������ȡ�����ϵ�����synchronized�ؼ��ֿ�Ӧ���ڷ�������(��������)�����Ǵ���鼶��(ϸ������)��
	public synchronized void produce(int neednum) {

		// �ж��ܷ��������
		while (neednum + curnum > max_size) {
			System.out.print(String.format("�����������������ͣ��������ǰ��棺%s", curnum));

			// ��ǰ�������̵߳ȴ����ó���
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// ��������
		curnum += neednum;
		System.out.println(String.format("�ٴ�����%s������ǰ��棺%s", neednum, curnum));

		// �����ڴ˶���������ϵȴ��������߳�
		notifyAll();
	}

	public synchronized void consume(int neednum) {
		// �ж��ܷ��������
		while (curnum < neednum) {
			System.out.println(String.format("��治�㣬�������ѣ���ǰ��棺%s", curnum));
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		curnum -= neednum;
		System.out.println(String.format("�Ѿ�����%s������ǰ��棺%s", neednum, curnum));

		// �����ڴ˶���������ϵȴ��������߳�
		notifyAll();
	}
}
