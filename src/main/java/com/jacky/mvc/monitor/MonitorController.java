package com.jacky.mvc.monitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jacky.dao.ILogDao;
import com.jacky.utils.ReturnResult;
import com.jacky.utils.aop.Log;
import com.jacky.utils.log.ISystemLogDao;
import com.jacky.utils.log.SystemLog;

/**
 * The monitor etc.
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:52
 * @since jdk1.8
 */
@Controller
public class MonitorController {
	@RequestMapping(value = "/monitor", method = RequestMethod.GET)
	@Log(methods = "����ҳ��", module = "ϵͳ����")
	public ModelAndView monitor(String id) {

		// ָ����ͼ
		ModelAndView mv = new ModelAndView("monitor/index");
		mv.addObject("test", "xxxxxxxxxxxxxxxxxx");

		return mv;
	}

	@Autowired
	private ILogDao systemLogDao;

	@RequestMapping("/monitor/log")
	@ResponseBody
	public ReturnResult<List<SystemLog>> querySystemLog(String bgtm, String edtm, String content, Long count, int page,
			int size) {
		List<SystemLog> listResult = new ArrayList<SystemLog>();

		if (bgtm == null || bgtm.isEmpty()) {
			bgtm = "1900-01-01 00:00:00";
		}
		if (edtm == null || edtm.isEmpty()) {
			edtm = "2099-12-31 23:59:59";
		}

		try {
			// MongoDB�ĵ�1ҳ�������0�������Ҫpage-1
			PageRequest pageRequest = new PageRequest(page - 1, size, new Sort(Direction.DESC, "id"));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date begin = null;
			Date end = null;
			try {
				begin = sdf.parse(bgtm);
				end = sdf.parse(edtm);
			} catch (ParseException e) {
				e.printStackTrace();
			}

//			if (count < 0) {
//				pageRequest = new PageRequest(page - 1, Integer.MAX_VALUE, new Sort(Direction.DESC, "id"));
//				Page<SystemLog> list = systemLogDao.findByOptTimeBetween(begin, end, content, pageRequest);
//
//				count = (long) list.getNumberOfElements();
//				if (count >= size) {
//					listResult = list.getContent().subList(0, size);
//				} else {
//					listResult = list.getContent().subList(0, count.intValue());
//				}
//
//			} else {
//				Page<SystemLog> list = systemLogDao.findByOptTimeBetween(begin, end, content, pageRequest);
//				listResult = list.getContent();
//			}

			listResult=systemLogDao.findByOptContentLike(content);
			count=(long) listResult.size();
			
			if (count >= size) {
				listResult = listResult.subList(0, size);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ReturnResult<List<SystemLog>>("001", e.toString(), listResult, count);
		}

		return new ReturnResult<List<SystemLog>>("000", "", listResult, count);
	}

	@RequestMapping(value = "/monitor/log1", method = RequestMethod.GET)
	@ResponseBody // @responseBodyע��������ǽ�controller�ķ������صĶ���ͨ���ʵ���ת����ת��Ϊָ���ĸ�ʽ֮��д�뵽response�����body����ͨ����������JSON���ݻ�����XML
	@Log(methods = "��־��ѯ", module = "ϵͳ����")
	public ReturnResult<List<SystemLog>> queryLog(String id) {

		List<SystemLog> listResult = new ArrayList<SystemLog>();

		listResult.add(new SystemLog("userID", "optModule", "optType", "optContent", "remark", 0L, "loginIP", (short) 0,
				"actionUrl"));
		listResult.add(new SystemLog("userID", "optModule", "optType", "optContent", "remark", 0L, "loginIP", (short) 0,
				"actionUrl"));
		listResult.add(new SystemLog("userID", "optModule", "optType", "optContent", "remark", 0L, "loginIP", (short) 0,
				"actionUrl"));
		listResult.add(new SystemLog("userID", "optModule", "optType", "optContent", "remark", 0L, "loginIP", (short) 0,
				"actionUrl"));
		listResult.add(new SystemLog("userID", "optModule", "optType", "optContent", "remark", 0L, "loginIP", (short) 0,
				"actionUrl"));
		return new ReturnResult<List<SystemLog>>("000", "", listResult, 123);
	}
}
