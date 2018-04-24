package cn.test.ssm.pojo;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: Student
 * @Description: TODO(学生信息pojo) 
 * @author: GK
 * @version V1.0
 */
public class Student {

	/**
	 * 主键ID，自增
	 */
	private Integer id;
	
	/**
	 * 学生姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 年龄
	 */
	private String age;
	
	/**
	 * 家庭住址
	 */
	private String address;
	
	/**
	 * 班级号
	 */
	private String classNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	
	/**
	 * 获取性别中文描述
	 * @return
	 */
	public String getSexDescr(){
		String sex = this.getSex();
		if(null != sex && !"".equals(sex)){
			return "1".equals(sex) ? "男" : "女";
		}
		return sex;
	}

}
