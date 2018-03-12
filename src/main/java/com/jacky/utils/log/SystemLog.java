package com.jacky.utils.log;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SystemLog")
public class SystemLog {

	@Id
	private String id;

	private Date optTime;

	private long actionTime;

	private String actionUrl;

	private String userID;

	private String optModule;

	private String optType;

	private String optContent;

	private String loginIP;

	private String remark;

	private short state;

	/**
	 * 默认构造函数
	 */
	public SystemLog() {

	}

	/**
	 * 带参构造函数
	 * 
	 * @param userID
	 *            用户ID,位于Principal
	 * @param optModule
	 *            操作模块
	 * @param optModule
	 *            操作类型
	 * @param optContent
	 *            日志内容
	 * @param remark
	 *            备注
	 */
	public SystemLog(String userID, String optModule, String optType, String optContent, String remark) {
		this.userID = userID;
		this.optModule = optModule;
		this.optType = optType;
		this.optContent = optContent;
		this.remark = remark;

		this.optTime = new Date();
	}

	public SystemLog(String userID, String optModule, String optType, String optContent, String remark, long actionTime,
			String loginIP, short state, String actionUrl) {
		this.userID = userID;
		this.optModule = optModule;
		this.optType = optType;
		this.optContent = optContent;
		this.remark = remark;
		this.actionTime = actionTime;
		this.loginIP = loginIP;
		this.state = state;
		this.actionUrl = actionUrl;
		this.optTime = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getOptModule() {
		return optModule;
	}

	public void setOptModule(String optModule) {
		this.optModule = optModule;
	}

	public String getOptContent() {
		return optContent;
	}

	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public long getActionTime() {
		return actionTime;
	}

	public void setActionTime(long actionTime) {
		this.actionTime = actionTime;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
}