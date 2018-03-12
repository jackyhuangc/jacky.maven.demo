package jacky.maven.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-02-06 16:42
 * @since jdk1.8
 */
public class T_Jdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// ��1������JDBC����������DriverManage���ҵ�
		Class.forName("com.mysql.jdbc.Driver");

		// ��2����������ȡ���ݿ�����
		Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root",
				"123456"); // �������ݿ�

		// ��3������JDBC Statements����,������PreparedStatement��Ԥ���룬Ч�ʸ�
		PreparedStatement ps = null;

		String sql = "select count(*) from users ";

		// ��4������SQL���Ĵ������
		// ps.setString(1, "");

		// ��5��ִ��SQL��䲢��ò�ѯ���
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		// ��6���Բ�ѯ�������ת������������������
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}

		// ��7���ͷ������Դ���ر�Connection���ر�Statement���ر�ResultSet��
		if (rs != null)
			rs.close();// ResultSet�����

		if (ps != null)
			ps.close();// �ر�Statement

		if (conn != null)
			conn.close();// �ر�����

	}
}
