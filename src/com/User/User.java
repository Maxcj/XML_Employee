package com.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.UserInfo.Info;

public class User {
	private int id;
	private String name;
	private String password;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public User(int id2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

	public static int findUser(String username, String password)
			throws FileNotFoundException, JDOMException, IOException {
		int flag = 0;
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/User/user_0.xml"));
		Element root = doc.getRootElement();
		List<Element> list = root.getChildren();
		for (Element element : list) {
			String name = element.getChildText("name");
			String pwd = element.getChildText("password");
			// System.out.println(username+" "+password);
			if (name.equals(username) && pwd.equals(password)) {
				return Integer.parseInt(element.getAttributeValue("id"));
			} else if (name.equals(username) && !pwd.equals(password)) {
				flag = -1;
			}
		}
		return flag;
	}

	public boolean saveUser() throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/User/user_0.xml"));
		Element root = doc.getRootElement();
		Element user = new Element("user");
		Element username = new Element("name");
		Element pwd = new Element("password");
		Attribute id = new Attribute("id", root.getContentSize() + "");
		username.addContent(name);
		pwd.addContent(password);
		user.setAttribute(id);
		user.addContent(username);
		user.addContent(pwd);
		root.addContent(user);
		Format f = Format.getPrettyFormat();
		f.setEncoding("utf-8");
		XMLOutputter xmlout = new XMLOutputter(f);
		xmlout.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/User/user_0.xml"));
		return true;
	}

	public boolean updatePwd(int user_id) {
		SAXBuilder sb = new SAXBuilder();
		Document doc;
		try {
			doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/User/user_0.xml"));
			Element root = doc.getRootElement();
			List<Element> ch = root.getChildren();
			for (Element f : ch) {
				String id = f.getAttributeValue("id");
				if (id == null)
					continue;
				if (id.equals(user_id + "")) {
					System.out.println("正在重置" + user_id + "的密码");
					Element password = f.getChild("password");
					password.setText("123456");

					Format f1 = Format.getPrettyFormat();
					f1.setEncoding("utf-8");
					XMLOutputter xmlOut = new XMLOutputter(f1);
					xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/User/user_0.xml"));
					System.out.println("重置密码完成");
					return true;
				}
			}
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean delete(int user_id) {
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/User/user_0.xml"));
			Element root = doc.getRootElement();
			List<Element> ch = root.getChildren();
			for (Element f : ch) {
				String id = f.getAttributeValue("id");
				System.out.println(id);
				if (id == null)
					continue;
				if (id.equals(user_id + "")) {
					System.out.println("正在删除员工");
					f.removeAttribute("id");
					f.removeChild("name");
					f.removeChild("password");
					ch.remove(f);

					Format f1 = Format.getPrettyFormat();
					f1.setEncoding("utf-8");
					XMLOutputter xmlOut = new XMLOutputter(f1);
					xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/User/user_0.xml"));
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}// delect

}
