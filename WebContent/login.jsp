<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="css/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript">
	function validate() {
		var username = document.getElementById("username");
		var password = document.getElementById("password");
		if (username.value == "") {
			alert("账号不能为空！");
			username.focus();
			return false;
		}
		if (password.value == "") {
			alert("密码不能为空！");
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
			<div class="panel panel-primary">
				<div class="panel-heading" align="center">登录</div>
				<div class="panel-body">
					<form action="UserSerlet?type=select" method="post"
						onsubmit="return validate();">

						<div class="form-group">
							<label>用户名:</label> <input type="text" class="form-control"
								name="username" id="username">
						</div>
						<div class="form-group">
							<label>密&nbsp;码:</label> <input type="password"
								class="form-control" name="password" id="password">
						</div>


						<div class="form-group">
							<div class="col-md-4 col-md-offset-3">
								<input type="submit" class="btn btn-primary" name="" value="登录">
							</div>
							<div>
								<input type="reset" class="btn btn-warning" name="" value="重置">
							</div>
						</div>
					</form>
					<div class="form-group">
						<p align="center">
							<br> <b>还没有账号？</b><a href="register.jsp">立即注册</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>