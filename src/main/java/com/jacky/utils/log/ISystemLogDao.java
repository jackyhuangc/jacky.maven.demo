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
 * 系统日志CRUD接口（MongoDB存储），接口命名规则：以大写字母"I"开头，如果有多个单词，每个单词头字母大写
 *
 * @author Jacky
 * @version 2017.12.18
 * @since 1.8
 */
public interface ISystemLogDao extends MongoRepository<SystemLog, ObjectId> {

	/**
	 * 自定义分页模糊查询 方法命名规则-首字母必须小写，如果该变量名有多个单词组成，后面的单词首字母 大写
	 *
	 * @param optContent
	 *            模糊查询的日志内容
	 * @param pageable
	 *            分页条件
	 *
	 * @return 日志列表
	 */
	Page<SystemLog> findByOptContentLike(String optContent, Pageable pageable);

	/**
	 * 根据起止时间查询日志数据 $gte 大于等于，$lte小于等于，请不要加''，否则无法识别
	 *
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param content
	 *            日志内容
	 * @param pageable
	 *            分页条件
	 *
	 * @return 日志列表
	 */
	@Query("{'OptTime':{$gte:?0,$lte:?1},'OptContent':{$regex:?2}}")
	Page<SystemLog> findByOptTimeBetween(Date beginTime, Date endTime, String content, Pageable pageable);
}