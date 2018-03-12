<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Admin Page</h1>
	<p>管理员页面</p>
	<form action="/auth/logout" method="post">

		<!-- 当开启了csrf时，需要用post方法且传入csrf.token才能正确注销 -->
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit" value="退出登录" />

	</form>
</body>
</html>
