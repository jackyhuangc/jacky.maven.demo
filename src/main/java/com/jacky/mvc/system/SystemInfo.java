package com.jacky.mvc.system;

/**
 * ϵͳ��Ϣ
 * 
 * @author huang
 *
 */
public class SystemInfo {

	public SystemInfo() {
	}

	/**
	 * ���캯��
	 * 
	 * @param id
	 *            ϵͳID
	 * @param name
	 *            ϵͳ����
	 * @param description
	 *            ϵͳ����
	 */
	public SystemInfo(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String id;

	private String name;

	private String image;
	
	private String description;
}
