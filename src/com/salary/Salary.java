package com.salary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.User.User;
import com.UserInfo.Info;

public class Salary extends User {
	private int salary_id;
	private String post_salary;
	private String attendance_salary;
	private String bonus;
	private String total_salary;
	private String date;

	public Salary(int id, String post_salary, String attendance_salary, String bonus, String total_salary,
			String date) {
		super(id);
		this.post_salary = post_salary;
		this.attendance_salary = attendance_salary;
		this.bonus = bonus;
		this.total_salary = total_salary;
		this.date = date;
	}

	public int getSalary_id() {
		return salary_id;
	}

	public void setSalary_id(int salary_id) {
		this.salary_id = salary_id;
	}

	public String getPost_salary() {
		return post_salary;
	}

	public void setPost_salary(String post_salary) {
		this.post_salary = post_salary;
	}

	public String getAttendance_salary() {
		return attendance_salary;
	}

	public void setAttendance_salary(String attendance_salary) {
		this.attendance_salary = attendance_salary;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getTotal_salary() {
		return total_salary;
	}

	public void setTotal_salary(String total_salary) {
		this.total_salary = total_salary;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Salary [salary_id=" + salary_id + ", post_salary=" + post_salary + ", attendance_salary="
				+ attendance_salary + ", bonus=" + bonus + ", total_salary=" + total_salary + ", date=" + date + "]";
	}

	public Salary(String post_salary, String attendance_salary, String bonus, String total_salary, String date) {
		super();
		this.post_salary = post_salary;
		this.attendance_salary = attendance_salary;
		this.bonus = bonus;
		this.total_salary = total_salary;
		this.date = date;
	}

	public static boolean AddUserSalary(Salary sal, int userid)
			throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/salary/salary.xml"));
		Element root = doc.getRootElement();

		Element Salary = new Element("Salary");
		Attribute salary_id = new Attribute("salary_id", root.getContentSize() + "");
		Attribute id = new Attribute("id", userid + "");
		Element post_salary = new Element("post_salary");
		Element attendance_salary = new Element("attendance_salary");
		Element bonus = new Element("bonus");
		Element total_salary = new Element("total_salary");
		Element date = new Element("date");

		post_salary.addContent(sal.getPost_salary());
		attendance_salary.addContent(sal.getAttendance_salary());
		bonus.addContent(sal.getBonus());
		total_salary.addContent(sal.getTotal_salary());
		date.addContent(sal.getDate());

		Salary.setAttribute(salary_id);
		Salary.setAttribute(id);
		Salary.addContent(post_salary);
		Salary.addContent(attendance_salary);
		Salary.addContent(bonus);
		Salary.addContent(total_salary);
		Salary.addContent(date);
		root.addContent(Salary);

		Format f = Format.getPrettyFormat();
		f.setEncoding("utf-8");
		XMLOutputter xmlOut = new XMLOutputter(f);
		xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/salary/salary.xml"));

		return true;
	}

	public static List<Salary> getUserAll(int user_id) {
		List<Salary> list = new ArrayList<Salary>();
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/salary/salary.xml"));
			Element root = doc.getRootElement();
			List<Element> ch = root.getChildren();
			for (Element element : ch) {
				if (Integer.valueOf(element.getAttributeValue("id")) == user_id) {
					Salary sal = new Salary();
					sal.setSalary_id(Integer.valueOf(element.getAttributeValue("salary_id")));
					sal.setId(Integer.valueOf(element.getAttributeValue("id")));
					sal.setPost_salary(element.getChildText("post_salary"));
					sal.setAttendance_salary(element.getChildText("attendance_salary"));
					sal.setBonus(element.getChildText("bonus"));
					sal.setTotal_salary(element.getChildText("total_salary"));
					sal.setDate(element.getChildText("date"));
					list.add(sal);
				}
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Salary get_a_month(int id, String date) {
		SAXBuilder sb = new SAXBuilder();
		Document doc;
		try {
			doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/salary/salary.xml"));
			Element root = doc.getRootElement();
			List<Element> ch = root.getChildren();
			for (Element element : ch) {

				if (Integer.valueOf(element.getAttributeValue("id")) == id
						&& element.getChildText("date").equals(date)) {
					Salary sal = new Salary();
					sal.setSalary_id(Integer.valueOf(element.getAttributeValue("salary_id")));
					sal.setId(Integer.valueOf(element.getAttributeValue("id")));

					sal.setPost_salary(element.getChildText("post_salary"));
					sal.setAttendance_salary(element.getChildText("attendance_salary"));
					sal.setBonus(element.getChildText("bonus"));
					sal.setTotal_salary(element.getChildText("total_salary"));
					sal.setDate(element.getChildText("date"));
					return sal;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
