<%@page import="com.salary.Salary"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的工资</title>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/resume.css" type="text/css" rel="stylesheet" />
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/scripts/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		function validate() {
			var search_id = document.getElementById("search_id");
			if (search_id.value == "") {
				alert("不能为空，请输入正确的数字！");
				search_id.focus();
				return false;
			}
			return true;
		}
	</script>

	<%
		int id = (Integer) request.getSession().getAttribute("id");
		if (id == 1) {
	%>
	<jsp:include page="AdminTop.jsp"></jsp:include>
	<%
		} else {
	%>
	<jsp:include page="top.jsp"></jsp:include>
	<%
		}
	%>


	<div class="container-fluid">

		<div class="table-responsive">
			<table class="table table-hover table-bordered"
				contenteditable="true">
				<%
					int num = 0;
					float sum_Post_salary = 0;
					float sum_Attendance_salary = 0;
					float sum_Bonus = 0;
					float sum_Total_salary = 0;
					int user_id = (Integer)request.getSession().getAttribute("user_id");

					if (id != 1) {
				%>
				<div class="panel panel-default">
					<div class="panel-body">
						<label>搜索当前月份工资：</label>
					</div>
					<form action="SalaryServlet?type=select&&user_id=<%=id %>"
						method="post">
						<div class="form-group col-sm-4">
							<p>
								<select class="form-control" name="salary_date">
									<option value="2017/01" selected="selected">2017年01月</option>
									<option value="2017/02">2017年02月</option>
									<option value="2017/03">2017年03月</option>
									<option value="2017/04">2017年04月</option>
									<option value="2017/05">2017年05月</option>
									<option value="2017/06">2017年06月</option>
									<option value="2017/07">2017年07月</option>
									<option value="2017/08">2017年08月</option>
									<option value="2017/09">2017年09月</option>
									<option value="2017/10">2017年10月</option>
									<option value="2017/11">2017年11月</option>
									<option value="2017/12">2017年12月</option>
								</select>
						</div>
						<input type="submit" value="搜索" class="btn btn-danger">
						</p>

					</form>
				</div>
				<%
					}
				%>

				<caption>工资明细</caption>
				<thead>
					<tr>
						<th>序号</th>
						<th>时间</th>
						<th>应发工资</th>
						<th>考勤</th>
						<th>奖金</th>
						<th>实发工资</th>
					</tr>
				</thead>
				<tbody>
					<%
						if (request.getAttribute("salary_date") == null) {
							//查询全部的工资
							
							List<Salary> salary_list = new Salary().getUserAll(user_id);
							for (Salary sal : salary_list) {
					%>

					<tr>
						<td><%=++num%></td>
						<td><%=sal.getDate()%></td>
						<td><%=sal.getPost_salary()%></td>
						<td><%=sal.getAttendance_salary()%></td>
						<td><%=sal.getBonus()%></td>
						<td style="color: red"><%=Integer.valueOf(sal.getPost_salary()) + Integer.valueOf(sal.getAttendance_salary())
							+ Integer.valueOf(sal.getBonus())%></td>
					</tr>

					<%
						sum_Post_salary += Integer.valueOf(sal.getPost_salary());
								sum_Attendance_salary += Integer.valueOf(sal.getAttendance_salary());
								sum_Bonus += Integer.valueOf(sal.getBonus());
								sum_Total_salary += (Integer.valueOf(sal.getPost_salary())
										+ Integer.valueOf(sal.getAttendance_salary()) + Integer.valueOf(sal.getBonus()));

							}
					%>
				</tbody>

				<table class="table table-hover table-bordered"
					contenteditable="true">
					<caption>全年工资统计</caption>
					<thead>
						<tr>
							<th>时间</th>
							<th>应发工资</th>
							<th>考勤</th>
							<th>奖金</th>
							<th>实发工资</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2017年</td>
							<td><%=sum_Post_salary%></td>
							<td><%=sum_Attendance_salary%></td>
							<td><%=sum_Bonus%></td>
							<td><%=sum_Total_salary%></td>

						</tr>

					</tbody>
				</table>

				<%
					} else {
						//查询一个月的工资
						String salary_date = (String)request.getAttribute("salary_date");
						System.out.println("查询的月份为："+request.getAttribute("salary_date"));
						Salary s = new Salary().get_a_month(user_id, salary_date);
				%>
				<tr>
					<td><%=++num %></td>
					<td><%=s.getDate()%></td>
					<td><%=s.getPost_salary()%></td>
					<td><%=s.getAttendance_salary()%></td>
					<td><%=s.getBonus()%></td>
					<td style="color: red"><%=Integer.valueOf(s.getPost_salary()) + Integer.valueOf(s.getAttendance_salary())
					+ Integer.valueOf(s.getBonus())%></td>
				</tr>
				<%
					}
				%>

			</table>
		</div>
	</div>
</body>
</html>