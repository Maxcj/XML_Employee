package com.salary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

/**
 * Servlet implementation class SalaryServlet
 */
@WebServlet("/SalaryServlet")
public class SalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalaryServlet() {
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
		if (type.equals("select")) {
			int user_id = Integer.valueOf(request.getParameter("user_id"));
			request.getSession().setAttribute("user_id", user_id);
			if (request.getParameter("salary_date") != null) {
				// 搜索一个月的工资
				System.out.println("查询一个月的");
				//request.getSession().setAttribute("salary_date", request.getParameter("salary_date"));
				request.setAttribute("salary_date", request.getParameter("salary_date"));

			} else {
				System.out.println("查询所有月份");
			}
			request.getRequestDispatcher("Salary.jsp").forward(request, response);
		} // select

		if (type.equals("add")) {
			String post_salary = request.getParameter("post_salary");
			String attendance_salary = request.getParameter("attendance_salary");
			String bonus = request.getParameter("bonus");
			int total_salary = Integer.valueOf(post_salary) + Integer.valueOf(attendance_salary)
					+ Integer.valueOf(bonus);
			String date = request.getParameter("date");
			int user_id = Integer.valueOf(request.getParameter("user_id"));
			System.out.println("user_id是：" + user_id);
			Salary sal = new Salary(post_salary, attendance_salary, bonus, total_salary + "", date);
			try {
				new Salary().AddUserSalary(sal, user_id);
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Salary> salary_list = new Salary().getUserAll(user_id);
			request.getSession().setAttribute("Sal_list", salary_list);
			request.getRequestDispatcher("Salary.jsp").forward(request, response);
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
