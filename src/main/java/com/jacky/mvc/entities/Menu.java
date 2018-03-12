package com.jacky.mvc.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-02-09 18:03
 * @since jdk1.8
 */
public class Menu implements Serializable {

	private String code;
	private String parentCode;
	private String title;
	private String icon;
	private String url;
	private int type;
	private String target;
	private List<Menu> child;

	public Menu() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}
}
