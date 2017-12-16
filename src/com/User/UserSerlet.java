package com.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.JDOMException;

import com.UserInfo.Info;

/**
 * Servlet implementation class UserSerlet
 */
@WebServlet("/UserSerlet")
public class UserSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSerlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String username = request.getParameter("username");
		String pwd = request.getParameter("password");

		if (type.equals("select")) {
			try {
				int id = User.findUser(username, pwd);
				System.out.println(id);
				if (id > 0) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("password", pwd);
					session.setAttribute("id", id);
					response.sendRedirect("allIndex.jsp");
				}
				if (id == -1) {
					out.println("<script type = 'text/javascript'>");
					out.println("alert('密码错误，请重新输入！');");
					out.println("window.location = 'login.jsp';");
					out.println("</script>");
				} else {
					out.println("<script type = 'text/javascript'>");
					out.println("alert('用户不存在，请先进行注册');");
					out.println("window.location = 'register.jsp';");
					out.println("</script>");
				}
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (type.equals("add")) {
			User user = new User(username, pwd);
			try {
				boolean a = user.saveUser();
				if (a) {
					out.println("<script type = 'text/javascript'>");
					out.println("alert('注册成功');");
					out.println("window.location = 'login.jsp';");
					out.println("</script>");
				} else {
					out.println("<script type = 'text/javascript'>");
					out.println("alert('注册失败');");
					out.println("window.location = 'register.jsp';");
					out.println("</script>");
				}
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (type.equals("delect")) {
			int user_id = Integer.valueOf(request.getParameter("user_id"));
			boolean b = new Info().deleteInfo(user_id);
			boolean a = new User().delete(user_id);
			if (a && b) {
				out.println("<script type = 'text/javascript'>");
				out.println("alert('删除员工成功');");
				out.println("window.location = 'All_ResumeServlet';");
				out.println("</script>");
			} else {
				out.println("<script type = 'text/javascript'>");
				out.println("alert('删除员工失败');");
				out.println("window.location = 'All_ResumeServlet';");
				out.println("</script>");
			}

		}

		if (type.equals("update")) {
			// 重置密码
			int user_id = Integer.valueOf(request.getParameter("user_id"));
			boolean a = new User().updatePwd(user_id);
			if (a) {
				out.println("<script type = 'text/javascript'>");
				out.println("alert('重置密码成功');");
				out.println("window.location = 'All_ResumeServlet';");
				out.println("</script>");
			} else {
				out.println("<script type = 'text/javascript'>");
				out.println("alert('重置密码失败');");
				out.println("window.location = 'All_ResumeServlet';");
				out.println("</script>");
			}
		}
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
