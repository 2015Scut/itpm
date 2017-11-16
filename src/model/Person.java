package model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 学生和教师的父类，提供姓名，年龄，性别属性的get、set方法
 * @author 钟恩俊
 *
 */
public class Person {
	/**姓名*/
	private String name;
	/**年龄*/
	private int age;
	/**性别*/
	private String sex;
	
	public Person() {
		
	}
	public Person(String n,int a,String s) {
		name=n;
		age=a;
		sex=s;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
