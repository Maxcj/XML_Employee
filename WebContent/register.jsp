<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">

<script type="text/javascript">
	function validate(){
		var username = document.getElementById("username");
		var password = document.getElementById("password");
		
		if(username.value == ""){
			alert("用户名不能为空！");
			username.focus();
			return false;
		}
		if(password.value == ""){
			alert("密码不能为空！");
			password.focus();
			return false;			
		}else if(password.value.length < 3 || password.value.length > 10){
			alert("密码长度不符合要求，请输入3~10位密码！");
			password.focus();
			return false;
		}
		return true;
	}
	

</script>
</head>
<body>


	<jsp:include page="top.jsp"></jsp:include>
	<div class="row login-box">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-success">

				<div class="panel-heading" align="center">注册</div>

				<div class="panel-body">

					<form action="UserSerlet?type=add" method="post"
						onsubmit="return validate();">



						<div class="form-group">
							<label>用户名:</label>
							<div class="form-group">
								<input type="text" class="form-control" name="username"
									id="username">
							</div>
						</div>

						<div class="form-group">
							<label>密&nbsp;码:</label> <input type="password"
								class="form-control" name="password" id="password">
						</div>
						<br>

						<div class="form-group">
							<div class="col-md-4 col-md-offset-3">
								<input type="submit" class="btn btn-success" value="立即注册">
							</div>
							<div>
								<input type="reset" class="btn btn-warning" value="重置">
							</div>
						</div>
					</form>
					<div class="form-group">
						<p align="center">
							<br> <b>已有账号？</b><a href="login.jsp">登录</a>
						</p>
					</div>

				</div>
			</div>
		</div>

	</div>

</body>

</html>

