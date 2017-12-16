<%@page import="com.UserInfo.Info"%>
<%@page import="com.salary.Salary"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<title>设置工资</title>
</head>
<body>
	<%
		int id = (Integer)request.getSession().getAttribute("id");
		if(id == 1){
			%>
	<jsp:include page="AdminTop.jsp"></jsp:include>
	<%
		}else{
			%>
	<jsp:include page="top.jsp"></jsp:include>
	<% 
		}
	%>

	<div>

		<div class="col-md-8 col-md-offset-2">

			<div class="panel panel-default">
				<!-- 标题 -->
				<div class="panel-heading">
					<h3 class="panel-title">设置工资</h3>
				</div>

				<div class="panel-body">

					<div class="panel-body">
						<%
					int user_id = Integer.valueOf(request.getParameter("user_id"));
					Info info = new Info().getbyid(user_id);
					%>

						<!-- 表单 -->
						<form class="bs-example bs-example-form" role="form"
							action="SalaryServlet?type=add&&user_id=<%=user_id %>"
							method="post">


							<P>
								<label>将给: <font color="red"><%=info.getName()+"	id:("+user_id+")" %></font>发放工资
								</label>
							</P>

							<label>应发工资:</label>
							<div class="input-group">
								<span class="input-group-addon">￥</span><input type="text"
									class="form-control" name="post_salary" /> <span
									class="input-group-addon">.00</span>
							</div>
							<br> <label>考勤:</label>
							<div class="input-group">
								<span class="input-group-addon">￥</span><input type="text"
									class="form-control" name="attendance_salary" /> <span
									class="input-group-addon">.00</span>
							</div>
							<br> <label>奖金:</label>
							<div class="input-group">
								<span class="input-group-addon">￥</span><input type="text"
									class="form-control" name="bonus" /> <span
									class="input-group-addon">.00</span>
							</div>
							<br> <label>时间:</label>
							<div class="form-group">
								<select class="form-control" name="date">
									<option value="2017/12">2017年12月</option>
									<option value="2017/11">2017年11月</option>
									<option value="2017/10">2017年10月</option>
									<option value="2017/09">2017年09月</option>
									<option value="2017/08">2017年08月</option>
									<option value="2017/07">2017年07月</option>
									<option value="2017/06">2017年06月</option>
									<option value="2017/05">2017年05月</option>
									<option value="2017/04">2017年04月</option>
									<option value="2017/03">2017年03月</option>
									<option value="2017/02">2017年02月</option>
									<option value="2017/01">2017年01月</option>
								</select>
							</div>


							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-10">
									<button type="submit" class="btn btn-primary">确定添加</button>

									<button type="reset" class="btn btn-warning">清空</button>
								</div>
							</div>
						</form>


					</div>
				</div>
			</div>

		</div>

	</div>


</body>
</html>