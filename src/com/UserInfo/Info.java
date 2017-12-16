package com.UserInfo;

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

public class Info extends User {
	private int info_id;
	private String name;
	private String email;
	private String phone;
	private String sex;
	private String deprt;

	public Info(int id, String name, String email, String phone, String sex, String deprt) {
		super(id);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.deprt = deprt;
	}

	public String getDeprt() {
		return deprt;
	}

	public void setDeprt(String deprt) {
		this.deprt = deprt;
	}

	public int getInfo_id() {
		return info_id;
	}

	public void setInfo_id(int info_id) {
		this.info_id = info_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Info() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Info(String name, String email, String phone, String sex, String deprt) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.deprt = deprt;
	}

	@Override
	public String toString() {
		return "Info [info_id=" + info_id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", sex=" + sex
				+ ", deprt=" + deprt + ", getId()=" + getId() + "]";
	}

	@SuppressWarnings("unchecked")
	public static Info getbyid(int id) {
		SAXBuilder sb = new SAXBuilder();
		Document doc;
		try {
			doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/UserInfo/UserInfo.xml"));
			Element root = doc.getRootElement();
			List<Element> ch = root.getChildren();
			for (Element element : ch) {
				if (Integer.valueOf(element.getAttributeValue("id")) == id) {
					Info info = new Info();
					info.setInfo_id(Integer.valueOf(element.getAttributeValue("info_id")));
					info.setId(Integer.valueOf(element.getAttributeValue("id")));
					info.setName(element.getChildText("name"));
					info.setEmail(element.getChildText("email"));
					info.setPhone(element.getChildText("phone"));
					info.setSex(element.getChildText("sex"));
					info.setDeprt(element.getChildText("deprt"));
					return info;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Info> getAll() {
		List<Info> list = new ArrayList<Info>();
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/UserInfo/UserInfo.xml"));
			Element root = doc.getRootElement();
			List<Element> ch = root.getChildren();
			for (Element element : ch) {
				Info info = new Info();
				info.setInfo_id(Integer.valueOf(element.getAttributeValue("info_id")));
				info.setId(Integer.valueOf(element.getAttributeValue("id")));
				info.setName(element.getChildText("name"));
				info.setEmail(element.getChildText("email"));
				info.setPhone(element.getChildText("phone"));
				info.setSex(element.getChildText("sex"));
				info.setDeprt(element.getChildText("deprt"));
				list.add(info);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean AddUserInfo(Info info, int userid) throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/UserInfo/UserInfo.xml"));
		Element root = doc.getRootElement();
		Element userInfo = new Element("userInfo");

		Attribute info_id = new Attribute("info_id", root.getContentSize() + "");
		Attribute id = new Attribute("id", userid + "");

		Element name = new Element("name");
		Element email = new Element("email");
		Element phone = new Element("phone");
		Element sex = new Element("sex");
		Element deprt = new Element("deprt");

		name.addContent(info.getName());
		email.addContent(info.getEmail());
		phone.addContent(info.getPhone());
		sex.addContent(info.getSex());
		deprt.addContent(info.getDeprt());

		userInfo.setAttribute(info_id);
		userInfo.setAttribute(id);
		userInfo.addContent(name);
		userInfo.addContent(email);
		userInfo.addContent(phone);
		userInfo.addContent(sex);
		userInfo.addContent(deprt);

		root.addContent(userInfo);

		Format f = Format.getPrettyFormat();
		f.setEncoding("utf-8");
		XMLOutputter xmlOut = new XMLOutputter(f);
		xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/UserInfo/UserInfo.xml"));
		return true;
	}

	public boolean update(Info info, int user_id) {
		SAXBuilder sb = new SAXBuilder();
		Document doc;
		try {
			doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/UserInfo/UserInfo.xml"));
			Element root = doc.getRootElement();
			List<Element> ch = root.getChildren();
			for (Element f : ch) {
				String id = f.getAttributeValue("id");
				if (id == null)
					continue;
				if (id.equals(user_id + "")) {
					System.out.println("正在更新" + user_id + "的信息");

					Element name = f.getChild("name");
					// System.out.println(info.getName());
					name.setText(info.getName());

					Element email = f.getChild("email");
					email.setText(info.getEmail());

					Element phone = f.getChild("phone");
					phone.setText(info.getPhone());

					Element sex = f.getChild("sex");
					sex.setText(info.getSex());

					Element deprt = f.getChild("deprt");
					deprt.setText(info.getDeprt());

					Format f1 = Format.getPrettyFormat();
					f1.setEncoding("utf-8");
					XMLOutputter xmlOut = new XMLOutputter(f1);
					xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/UserInfo/UserInfo.xml"));
					System.out.println("更新完成");
					return true;
				}
			}

		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean deleteInfo(int user_id) {
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/UserInfo/UserInfo.xml"));
			Element root = doc.getRootElement();
			List<Element> ch = root.getChildren();
			for (Element f : ch) {
				String id = f.getAttributeValue("id");
				System.out.println(id);
				if (id == null)
					continue;
				if (id.equals(user_id + "")) {
					System.out.println("正在删除员工信息");
					f.removeAttribute("info_id");
					f.removeAttribute("id");
					f.removeChild("name");
					f.removeChild("email");
					f.removeChild("phone");
					f.removeChild("sex");
					f.removeChild("deprt");
					ch.remove(f);

					Format f1 = Format.getPrettyFormat();
					f1.setEncoding("utf-8");
					XMLOutputter xmlOut = new XMLOutputter(f1);
					xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/UserInfo/UserInfo.xml"));
					return true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
