package com.jacky.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.jacky.utils.log.SystemLog;

import java.util.Date;
import java.util.List;


public interface ILogDao {
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
	List<SystemLog> findByOptContentLike(String optContent);

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
	List<SystemLog> findByOptTimeBetween(Date beginTime, Date endTime, String content);
}