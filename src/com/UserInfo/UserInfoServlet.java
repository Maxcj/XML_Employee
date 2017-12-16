package com.UserInfo;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String type = request.getParameter("type");
		int id = (Integer) request.getSession().getAttribute("id");
	
		
		if (type.equals("add")) {
			int user_id = Integer.valueOf(request.getParameter("uesr_id"));
			String name = request.getParameter("username");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String sex = request.getParameter("sex");
			String deprt = request.getParameter("deprt");
			try {
				Info info = new Info(name, email, phone, sex, deprt);
				info.AddUserInfo(info, id);
				request.getSession().setAttribute("user_id", user_id);

			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (type.equals("select")) {
			
			int user_id = Integer.valueOf(request.getParameter("uesr_id"));
			request.getSession().setAttribute("user_id", user_id);

		}
		if (type.equals("update")) {
			int user_id = Integer.valueOf(request.getParameter("user_id"));
			String name = request.getParameter("username");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String sex = request.getParameter("sex");
			String deprt = request.getParameter("deprt");
			Info info = new Info(name, email, phone, sex, deprt);
			System.out.println(info);
			boolean a = new Info().update(info, user_id);
			System.out.println(a);
		}

		response.sendRedirect("Info.jsp");

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
