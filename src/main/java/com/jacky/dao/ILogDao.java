package com.jacky.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.jacky.utils.log.SystemLog;

import java.util.Date;
import java.util.List;


public interface ILogDao {
	/**
	 * �Զ����ҳģ����ѯ ������������-����ĸ����Сд������ñ������ж��������ɣ�����ĵ�������ĸ ��д
	 *
	 * @param optContent
	 *            ģ����ѯ����־����
	 * @param pageable
	 *            ��ҳ����
	 *
	 * @return ��־�б�
	 */
	List<SystemLog> findByOptContentLike(String optContent);

	/**
	 * ������ֹʱ���ѯ��־���� $gte ���ڵ��ڣ�$lteС�ڵ��ڣ��벻Ҫ��''�������޷�ʶ��
	 *
	 * @param beginTime
	 *            ��ʼʱ��
	 * @param endTime
	 *            ����ʱ��
	 * @param content
	 *            ��־����
	 * @param pageable
	 *            ��ҳ����
	 *
	 * @return ��־�б�
	 */
	List<SystemLog> findByOptTimeBetween(Date beginTime, Date endTime, String content);
}