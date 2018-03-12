<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>J-Mvc</title>
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<style type="text/css">
h2 {
	color: #fff;
}
</style>
</head>
<body>

	<div id="login-error">${error}</div>

	<h2>J-Mvc Login</h2>
	<form class='login-form' action="../j_spring_security_check"
		method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<div class="flex-row">
			<label class="lf--label" for="username"><i class="fa fa-user"></i>
			</label> <input id="username" name="username" class='lf--input'
				placeholder='Username' type='text'>
		</div>
		<div class="flex-row">
			<label class="lf--label" for="password"><i class="fa fa-key"></i>
			</label> <input id="password" name="password" class='lf--input'
				placeholder='Password' type='password'>
		</div>
		<input class='lf--submit' type='submit' value='LOGIN'>
	</form>
	<a class='lf--forgot' href='http://www.baidu.com'>Forgot password?</a>
</body>
</html>
