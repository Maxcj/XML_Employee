
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<title>添加我的基本信息</title>
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

		<div class="col-md-6 col-md-offset-3">

			<div class="panel panel-default">
				<!-- 标题 -->
				<div class="panel-heading">
					<h3 class="panel-title">添加我的基本信息</h3>
				</div>

				<div class="panel-body">

					<div class="panel-body">
						<!-- 表单 -->
						<form action="UserInfoServlet?type=add&&uesr_id=<%=id %>"
							method="post" class="form-horizontal">

							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>姓名:</label> <input type="text" class="form-control"
										name="username" id="username" placeholder="请输入您的姓名">
								</div>
							</div>



							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>电话:</label> <input type="text" class="form-control"
										name="phone" id="phone" placeholder="请输入您的电话号码">
								</div>


							</div>


							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>邮箱:</label> <input type="text" class="form-control"
										name="email" id="email" placeholder="请输入您的邮箱">
								</div>

							</div>

							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>性别:</label><br> <label class="radio-inline">
										<input type="radio" name="sex" value="男">男
									</label> <label class="radio-inline"> <input type="radio"
										name="sex" value="女">女
									</label>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-10 col-sm-offset-1">
									<label>部门:</label> <input type="text" class="form-control"
										name=deprt id="deprt" placeholder="请输入您的部门名称">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-5">
									<button type="submit" class="btn btn-primary">添加</button>
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