package com.notice;

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

public class Notice {
	private int notice_id;
	private String notice_title;
	private String notice_content;
	private String notice_date;

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}

	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notice(String notice_title, String notice_content, String notice_date) {
		super();
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_date = notice_date;
	}

	@Override
	public String toString() {
		return "Notice [notice_id=" + notice_id + ", notice_title=" + notice_title + ", notice_content="
				+ notice_content + ", notice_date=" + notice_date + "]";
	}

	public static boolean AddNotice(Notice no) throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/notice/Notice.xml"));
		Element root = doc.getRootElement();
		Element Notice = new Element("Notice");

		Attribute notice_id = new Attribute("notice_id", root.getContentSize() + "");
		Element notice_tittle = new Element("notice_tittle");
		Element notice_content = new Element("notice_content");
		Element notice_date = new Element("notice_date");

		notice_tittle.addContent(no.getNotice_title());
		notice_content.addContent(no.getNotice_content());
		notice_date.addContent(no.getNotice_date());

		Notice.setAttribute(notice_id);
		Notice.addContent(notice_tittle);
		Notice.addContent(notice_content);
		Notice.addContent(notice_date);

		root.addContent(Notice);

		Format f = Format.getPrettyFormat();
		f.setEncoding("utf-8");
		XMLOutputter xmlOut = new XMLOutputter(f);
		xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/notice/Notice.xml"));
		System.out.println("添加通知成功");
		return true;
	}

	public static List<Notice> getAll() {
		List<Notice> list = new ArrayList<Notice>();
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/notice/Notice.xml"));
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> ch = root.getChildren();
			for (Element element : ch) {
				Notice no = new Notice();
				no.setNotice_id(Integer.valueOf(element.getAttributeValue("notice_id")));
				no.setNotice_title(element.getChildText("notice_tittle"));
				no.setNotice_content(element.getChildText("notice_content"));
				no.setNotice_date(element.getChildText("notice_date"));
				list.add(no);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(Notice no, int notice_id) {
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/notice/Notice.xml"));
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> ch = root.getChildren();
			for (Element f : ch) {
				String no_id = f.getAttributeValue("notice_id");
				if (no_id.equals(notice_id + "")) {
					System.out.println("正在更新第" + notice_id + "个通知");

					Element notice_tittle = f.getChild("notice_tittle");
					notice_tittle.setText(no.getNotice_title());

					Element notice_content = f.getChild("notice_content");
					notice_content.setText(no.getNotice_content());

					Element notice_date = f.getChild("notice_date");
					notice_date.setText(no.getNotice_date());

					Format f1 = Format.getPrettyFormat();
					f1.setEncoding("utf-8");
					XMLOutputter xmlOut = new XMLOutputter(f1);
					xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/notice/Notice.xml"));
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

	public static boolean delete(int notice_id) {
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/notice/Notice.xml"));
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> ch = root.getChildren();
			for (Element f : ch) {
				String id = f.getAttributeValue("notice_id");
				if (id == null)
					continue;
				if (id.equals(notice_id + "")) {
					System.out.println("正在删除通知");
					f.removeAttribute("notice_id");
					f.removeChild("notice_tittle");
					f.removeChild("notice_content");
					f.removeChild("notice_date");

					Format f1 = Format.getPrettyFormat();
					f1.setEncoding("utf-8");
					XMLOutputter xmlOut = new XMLOutputter(f1);
					xmlOut.output(doc, new FileOutputStream("E:/Code/XML/xml/src/com/notice/Notice.xml"));
					System.out.println("删除通知成功");
					return true;
				}
			}
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static Notice getbyID(int notice_id) {
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(new FileInputStream("E:/Code/XML/xml/src/com/notice/Notice.xml"));
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> ch = root.getChildren();
			for (Element element : ch) {
				if (Integer.valueOf(element.getAttributeValue("notice_id")) == notice_id) {
					Notice no = new Notice();
					no.setNotice_id(Integer.valueOf(element.getAttributeValue("notice_id")));
					no.setNotice_title(element.getChildText("notice_tittle"));
					no.setNotice_content(element.getChildText("notice_content"));
					no.setNotice_date(element.getChildText("notice_date"));
					return no;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
