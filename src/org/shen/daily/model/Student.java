package org.shen.daily.model;

/**
 * @author ZhiqiangShen 
 * ѧ����Ӧ��ʵ����
 */
public class Student {

	/**
	 * ѧ��
	 */
	private Long id;

	/**
	 * ����
	 */
	private String name;

	/**
	 * �Ա�
	 */
	private Byte sex;

	/**
	 * �绰����
	 */
	private Long tel;

	/**
	 * ����
	 */
	private String email;

	public Student() {
		super();
	}

	public Student(Long id, String name, Byte sex, Long tel, String email) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", tel=" + tel + ", email=" + email + "]";
	}
	
	
}
