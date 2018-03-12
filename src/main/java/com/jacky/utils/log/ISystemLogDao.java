package com.jacky.utils.log;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 
 * ϵͳ��־CRUD�ӿڣ�MongoDB�洢�����ӿ����������Դ�д��ĸ"I"��ͷ������ж�����ʣ�ÿ������ͷ��ĸ��д
 *
 * @author Jacky
 * @version 2017.12.18
 * @since 1.8
 */
public interface ISystemLogDao extends MongoRepository<SystemLog, ObjectId> {

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
	Page<SystemLog> findByOptContentLike(String optContent, Pageable pageable);

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
	@Query("{'OptTime':{$gte:?0,$lte:?1},'OptContent':{$regex:?2}}")
	Page<SystemLog> findByOptTimeBetween(Date beginTime, Date endTime, String content, Pageable pageable);
}