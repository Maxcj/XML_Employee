
<%@page import="com.UserInfo.Info"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<title>查看信息</title>
</head>
<body>
	<%
		int id = (Integer)request.getSession().getAttribute("id");
		System.out.println("info这里");
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
					<h3 class="panel-title">查看信息</h3>
				</div>

				<div class="panel-body">

					<div class="panel-body">
						<!-- 表单 -->
						<form action="#" method="post" class="form-horizontal">

							<%
					System.out.println("这里");
					int user_id = (Integer)request.getSession().getAttribute("user_id");
					System.out.println("info的="+user_id);
					Info dao = new Info();
						Info info = dao.getbyid(user_id);
						System.out.println(info);
					%>


							<div class="form-group col-sm-8">
								<label>姓名：<%=info.getName() %></label>
							</div>

							<div class="form-group col-sm-8">
								<label>电话：<%=info.getPhone() %></label>
							</div>

							<div class="form-group col-sm-8">
								<label>Email：<%=info.getEmail() %></label>
							</div>

							<div class="form-group col-sm-8">
								<label>性别：<%=info.getSex() %></label>
							</div>

							<div class="form-group col-sm-8">
								<label>部门：<%=info.getDeprt() %></label>
							</div>



							<p>
								<img src="images/person_img.jpg" class="img-rounded">
							</p>

							<div class="form-group col-sm-8">
								<a href="UpdateInfo.jsp?user_id=<%=info.getId() %>"
									class="btn btn-primary" role="button">修改信息</a>
							</div>
						</form>
					</div>
				</div>
			</div>

		</div>

	</div>






</body>
</html>