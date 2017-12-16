<%@page import="com.UserInfo.Info"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<title>修改信息界面</title>
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
					<h3 class="panel-title">修改信息</h3>
				</div>

				<div class="panel-body">

					<div class="panel-body">
						<!-- 表单 -->
						<%
					int user_id = Integer.valueOf(request.getParameter("user_id"));
					Info dao = new Info();
						Info info = dao.getbyid(user_id);
						System.out.println("efwege:"+user_id);
					%>
						<form action="UserInfoServlet?type=update&&user_id=<%=user_id %>"
							method="post" class="form-horizontal">

							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>姓名:</label> <input type="text" class="form-control"
										name=username id="username" value="<%=info.getName() %>"
										placeholder="请输入您的姓名">
								</div>
							</div>


							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>电话:</label> <input type="text" class="form-control"
										name=phone id="phone" value="<%=info.getPhone() %>"
										placeholder="请输入您的电话号码">
								</div>
							</div>


							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>Email:</label> <input type="text" class="form-control"
										name=email id="eamil" value="<%=info.getEmail() %>"
										placeholder="请输入您的邮箱地址">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>性别:</label><br>
									<%	if(info.getSex().equals("男")){
									%>
									<label class="radio-inline"><input type="radio"
										name="sex" value="男" checked="checked">男</label> <label
										class="radio-inline"> <input type="radio" name="sex"
										value="女">女
									</label>
									<%
								}else if(info.getSex().equals("女")){
									%>
									<label class="radio-inline"><input type="radio"
										name="sex" value="男">男</label> <label class="radio-inline">
										<input type="radio" name="sex" value="女" checked="checked">女
									</label>

									<%}%>

								</div>
							</div>


							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>部门:</label> <input type="text" class="form-control"
										name=deprt id="deprt" value="<%=info.getDeprt() %>"
										placeholder="请输入您的部门名称">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-10">
									<button type="submit" class="btn btn-primary">修改</button>

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