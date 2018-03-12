package com.jacky.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.jacky.dao.ILogDao;
import com.jacky.utils.log.SystemLog;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-02-11 13:52
 * @since jdk1.8
 */
@Service
public class LogDaoImpl implements ILogDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<SystemLog> findByOptContentLike(String optContent) {

		Query query = new Query();
		Criteria criteria = Criteria.where("optContent").regex(optContent);
		query.addCriteria(criteria);
		List<SystemLog> listResult = this.mongoTemplate.find(query, SystemLog.class);

		return listResult;
	}

	@Override
	public List<SystemLog> findByOptTimeBetween(Date beginTime, Date endTime, String content) {
		Query query = new Query();

		// mongodb≤È—Ø”Ôæ‰ http://blog.csdn.net/congcong68/article/details/47183209
		Criteria criteria = Criteria.where("optContent").regex(content).and("optTime").gte(beginTime);
		// query.addCriteria(criteria);
		List<SystemLog> listResult = this.mongoTemplate.find(query, SystemLog.class);
		return null;
	}

}
