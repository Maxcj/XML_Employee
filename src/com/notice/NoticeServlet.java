package com.notice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String type = request.getParameter("type");
		if (type.equals("add")) {
			Notice no = this.requestDataObj(request);
			try {
				new Notice();
				Notice.AddNotice(no);

			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getRequestDispatcher("Notice.jsp").forward(request, response);
		}
		if (type.equals("select")) {

			request.getRequestDispatcher("Notice.jsp").forward(request, response);
		}
		/*
		 * if(type.equals("update")){ int notice_id =
		 * Integer.valueOf(request.getParameter("notice_id")); Notice no =
		 * this.requestDataObj(request); new Notice().update(no, notice_id);
		 * boolean a = new Notice().update(no, notice_id);
		 * 
		 * Notice notice = new Notice().getbyID(notice_id);
		 * request.getSession().setAttribute("no_list", notice);
		 * request.getRequestDispatcher("ViewNotice.jsp").forward(request,
		 * response);
		 * 
		 * }
		 */
		if (type.equals("delect")) {
			int notice_id = Integer.valueOf(request.getParameter("notice_id"));
			new Notice();
			Notice.delete(notice_id);

			request.getRequestDispatcher("Notice.jsp").forward(request, response);
		}
		if (type.equals("view")) {
			int notice_id = Integer.valueOf(request.getParameter("notice_id"));
			new Notice();
			Notice no = Notice.getbyID(notice_id);
			request.getSession().setAttribute("no_list", no);
			request.getRequestDispatcher("ViewNotice.jsp").forward(request, response);
		}

	}

	private Notice requestDataObj(HttpServletRequest request) {
		Notice no = new Notice();
		String title = request.getParameter("notice_title");
		String content = request.getParameter("notice_content");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		no = new Notice(title, content, date);
		return no;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
